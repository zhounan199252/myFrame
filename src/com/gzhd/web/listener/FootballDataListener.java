package com.gzhd.web.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzhd.model.MatchResultModel;
import com.gzhd.service.itf.MatchResultService;
import com.gzhd.util.StringUtil;
import com.gzhd.util.TimeUtil;

public class FootballDataListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(FootballDataListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		final ServletContext application = sce.getServletContext();
		
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		final MatchResultService matchResultService = (MatchResultService) ac.getBean(MatchResultService.BEAN_NAME);

		Timer timer = new Timer();
		Timer timer2 = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				getFootballData(application, matchResultService);
			}
		}, 2000, 1000 * 60 * 10);

		timer2.schedule(new TimerTask() {

			@Override
			public void run() {
				application.setAttribute("currentTime", TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss"));
			}
		}, 2000, 1000);
	}

	/**
	 * @param 获取足球
	 *            上一天，当天，后一天的赛事数据
	 */
	/**
	 * @param application
	 */
	private void getFootballData(ServletContext application, MatchResultService matchResultService) {

		String dataUrl = "http://api.caipiaokong.com/live/?name=jczq&format=json&uid=420845&token=4d5aa35ebeb48e7d3e4c8f7e16e1c7942c4cebee&phase=";

		BufferedReader br = null;
		URL url = null;
		HttpURLConnection con = null;
		StringBuilder response = null;
		String inputLine = "";

		try {
			String lastDayUrl = dataUrl + TimeUtil.dateFormat(TimeUtil.getDateRelateToDate(new Date(), -1), "yyyyMMdd");
			url = new URL(lastDayUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			response = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				response.append(StringUtil.convert(inputLine));
			}

			String json = null;

			if (response.toString().length() > 100) {

				json = response.toString().replaceAll("\"0\"", "\"t0\"").replaceAll("\"1\"", "\"t1\"").replaceAll("\"3\"", "\"t3\"");
				application.setAttribute("currentDayData", json);
				// logger.info(json);

				JSONArray jsonArray = JSONArray.parseArray(json);

				MatchResultModel model = null;
				for (int i = 0; i < jsonArray.size(); i++) {

					JSONObject currentObj = jsonArray.getJSONObject(i);

					if (StringUtils.isNotBlank(currentObj.getString("final_score"))) { // 如果赛事有结果，也就是赛事已经结束

						if (!matchResultService.checkResultIsExistByMatchId(currentObj.getString("id"))) { // 如果结果还没保存到数据库
							model = new MatchResultModel();

							String score = currentObj.getString("final_score");
							String handicapString = currentObj.getString("handicap");

							model.setMatchId(currentObj.getString("id"));
							model.setMatchTime(currentObj.getString("match_date"));
							model.setResult(score);
							model.setType("football");

							String[] scores = score.split(":");

							StringBuilder sb = new StringBuilder();

							int homeScore = Integer.parseInt(scores[0]); // 主队分数
							int awayScore = Integer.parseInt(scores[1]); // 客队分数
							int handicap = Integer.parseInt(handicapString);

							if (homeScore > awayScore) {
								sb.append("胜;");
							} else if (homeScore == awayScore) {
								sb.append("平;");
							} else if (homeScore < awayScore) {
								sb.append("负;");
							}

							if (homeScore + handicap > awayScore) {
								sb.append("让球胜");
							} else if (homeScore + handicap == awayScore) {
								sb.append("让球平");
							} else if (homeScore + handicap < awayScore) {
								sb.append("让球负");
							}

							String winOrLose = sb.toString();
							model.setWinOrLose(winOrLose);

							matchResultService.addMatchResult(model); // 把结果保存到数据库
						}
					}
				}
			}

			Thread.sleep(5000); // 先睡眠5秒，否则接口网站会认为恶意操作
			// ==========================================
			String nextDayUrl = dataUrl + TimeUtil.getCurDate("yyyyMMdd");
			url = new URL(nextDayUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			response = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				response.append(StringUtil.convert(inputLine));
			}

			if (response.toString().length() > 100) {

				json = response.toString().replaceAll("\"0\"", "\"t0\"").replaceAll("\"1\"", "\"t1\"").replaceAll("\"3\"", "\"t3\"");
				application.setAttribute("nextDayData", json);

				JSONArray jsonArray = JSONArray.parseArray(json);

				MatchResultModel model = null;
				for (int i = 0; i < jsonArray.size(); i++) {

					JSONObject currentObj = jsonArray.getJSONObject(i);

					if (StringUtils.isNotBlank(currentObj.getString("final_score"))) {
						if (!matchResultService.checkResultIsExistByMatchId(currentObj.getString("id"))) {
							model = new MatchResultModel();

							String score = currentObj.getString("final_score");
							String handicapString = currentObj.getString("handicap");

							model.setMatchId(currentObj.getString("id"));
							model.setMatchTime(currentObj.getString("match_date"));
							model.setResult(score);
							model.setType("football");

							String[] scores = score.split(":");

							StringBuilder sb = new StringBuilder();

							int homeScore = Integer.parseInt(scores[0]); // 主队分数
							int awayScore = Integer.parseInt(scores[1]); // 客队分数
							int handicap = Integer.parseInt(handicapString);

							if (homeScore > awayScore) {
								sb.append("胜;");
							} else if (homeScore == awayScore) {
								sb.append("平;");
							} else if (homeScore < awayScore) {
								sb.append("负;");
							}

							if (homeScore + handicap > awayScore) {
								sb.append("让球胜");
							} else if (homeScore + handicap == awayScore) {
								sb.append("让球平");
							} else if (homeScore + handicap < awayScore) {
								sb.append("让球负");
							}

							String winOrLose = sb.toString();
							model.setWinOrLose(winOrLose);

							matchResultService.addMatchResult(model); // 把结果保存到数据库
						}
					}
				}
			}
			Thread.sleep(5000);

			// ==========================================
			String next2DayUrl = dataUrl + TimeUtil.dateFormat(TimeUtil.getDateRelateToDate(new Date(), 1), "yyyyMMdd");
			url = new URL(next2DayUrl);

			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			response = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				response.append(StringUtil.convert(inputLine));
			}

			if (response.toString().length() > 100) {
				json = response.toString().replaceAll("\"0\"", "\"t0\"").replaceAll("\"1\"", "\"t1\"").replaceAll("\"3\"", "\"t3\"");
				application.setAttribute("next2DayData", json);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (Exception e1) {
				logger.error(e1);
				e1.printStackTrace();
			}
		}
	}
}
