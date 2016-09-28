package com.gzhd.web.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gzhd.model.FootballBetModel;
import com.gzhd.service.itf.FootballBetService;
import com.gzhd.service.itf.MatchResultService;

public class CheckMatchResultListener implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(CheckMatchResultListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		final ServletContext application = sce.getServletContext();

		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		final MatchResultService matchResultService = (MatchResultService) ac.getBean(MatchResultService.BEAN_NAME);
		final FootballBetService footballBetService = (FootballBetService) ac.getBean(FootballBetService.BEAN_NAME);

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				checkResult(matchResultService, footballBetService);
			}

		}, 40000, 10 * 60 * 1000);
	}

	private void checkResult(MatchResultService matchResultService, FootballBetService footballBetService) {

		Map<String, String> resultMap = matchResultService.getMatchResultByDate();
		List<FootballBetModel> footballBetList = footballBetService.getNotFulfilFootballBet();
		logger.error("===============================");
		for(Entry<String, String> e : resultMap.entrySet()) {
			logger.error(e.getKey() + " : " + e.getValue());
		}
		
		for (FootballBetModel footballBetModel : footballBetList) {
			logger.error(footballBetModel.getMatchId() + " , " + footballBetModel.getWinOrLose() + " , " + footballBetModel.getHomeTeam());
		}
		logger.error("===============================");
		

		List<String> existSeriesNumList = new ArrayList<String>();

		for (FootballBetModel model : footballBetList) {

			if ("series".equals(model.getType())) {

				if (!existSeriesNumList.contains(model.getSeriesNum())) {
					List<FootballBetModel> footballBetListOfSeriesNum = footballBetService.getFootballBetBySeriesNum(model.getSeriesNum());

					boolean isValid = true;
					boolean isAllMatchIsOk = true;

					for (FootballBetModel betModel : footballBetListOfSeriesNum) {

						String result = resultMap.get(betModel.getMatchId()); // 根据赛事id拿到比赛结果

						if (StringUtils.isNotBlank(result)) {
							String winOrLose = betModel.getWinOrLose();

							String[] results = result.split(";");

							if (!results[0].trim().equals(winOrLose.trim()) && !results[1].trim().equals(winOrLose.trim())) { // 没投中
								isValid = false;
							}
						} else {
							isAllMatchIsOk = false; // 多串一的赛事中其中一场或几场还没开奖
							break;
						}
					}

					if (isValid && isAllMatchIsOk) { // 全部都投中了
						footballBetService.updateFootballBetWinningBySeriesNum("true", model.getSeriesNum());
					} else if (!isValid && isAllMatchIsOk) {
						footballBetService.updateFootballBetWinningBySeriesNum("false", model.getSeriesNum());
					}
					existSeriesNumList.add(model.getSeriesNum());
				}
			} else {

				String result = resultMap.get(model.getMatchId());

				if (StringUtils.isNotBlank(result)) {
					String winOrLose = model.getWinOrLose();

					String[] results = result.split(";");

					if (!results[0].trim().equals(winOrLose.trim()) && !results[1].trim().equals(winOrLose.trim())) {
						footballBetService.updateFootballBetWinningById("false", model.getId());
					} else {
						footballBetService.updateFootballBetWinningById("true", model.getId());
					}
				}
			}
		}
	}
}
