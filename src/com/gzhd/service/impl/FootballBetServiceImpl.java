package com.gzhd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gzhd.dao.itf.BaseDao;
import com.gzhd.domain.BackUser;
import com.gzhd.domain.FootballBet;
import com.gzhd.domain.FrontUser;
import com.gzhd.model.FootballBetModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.FootballBetService;
import com.gzhd.util.FormatUtil;
import com.gzhd.util.StringUtil;

@Service(value = FootballBetService.BEAN_NAME)
public class FootballBetServiceImpl implements FootballBetService {

	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<FootballBet> baseDao;

	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<FrontUser> frontUserDao;

	@Override
	public String addFootballBet(FootballBetModel model, String userId, String curTime) {

		FrontUser frontUser = frontUserDao.get(FrontUser.class, userId);

		double balance = frontUser.getBalance();
		double consumption = frontUser.getConsumption();
		double remain = balance - model.getTotalCount();
		double consumptionCount = consumption + model.getTotalCount();

		if (remain < 0) { // 如果余额不足
			return "";
		}

		String[] datas = model.getData().split("&");

		FootballBet footballBet = null;

		if ("single".equals(model.getType())) {
			for (String singleData : datas) {

				// 33676;英格兰;威尔士;2016-06-16 20:45:00;1.42;无;胜;倍数
				String[] singleDatas = singleData.split(";");

				footballBet = new FootballBet();

				footballBet.setHomeTeam(singleDatas[1]);
				footballBet.setAwayTeam(singleDatas[2]);
				footballBet.setOdds(Double.parseDouble(singleDatas[4]));
				footballBet.setBetTime(curTime);
				footballBet.setMultiple(Integer.parseInt(singleDatas[8]));
				footballBet.setUser(frontUser);
				footballBet.setRangQiu(singleDatas[5]);
				footballBet.setWinOrLose(singleDatas[6]);
				footballBet.setMatchTime(singleDatas[7]);
				footballBet.setType(singleDatas[9]);
				footballBet.setIsFulfil("no");
				footballBet.setSeriesNum("无");
				footballBet.setMatchType(singleDatas[10]);
				footballBet.setMatchId(singleDatas[0]);
				footballBet.setIsWinning("no");

				baseDao.save(footballBet);
			}
		} else {
			String seriesNum = StringUtil.generateSeriesNum();
			for (String singleData : datas) {

				// 33676;英格兰;威尔士;2016-06-16 20:45:00;1.42;无;胜;倍数
				String[] singleDatas = singleData.split(";");

				footballBet = new FootballBet();

				footballBet.setHomeTeam(singleDatas[1]);
				footballBet.setAwayTeam(singleDatas[2]);
				footballBet.setOdds(Double.parseDouble(singleDatas[4]));
				footballBet.setBetTime(curTime);
				footballBet.setMultiple(Integer.parseInt(singleDatas[8]));
				footballBet.setUser(frontUser);
				footballBet.setRangQiu(singleDatas[5]);
				footballBet.setWinOrLose(singleDatas[6]);
				footballBet.setMatchTime(singleDatas[7]);
				footballBet.setType(singleDatas[9]);
				footballBet.setSeriesNum(seriesNum);
				footballBet.setIsFulfil("no");
				footballBet.setMatchType(singleDatas[10]);
				footballBet.setMatchId(singleDatas[0]);
				footballBet.setIsWinning("no");

				baseDao.save(footballBet);
			}
		}

		frontUser.setBalance(remain);
		frontUser.setConsumption(consumptionCount);

		frontUserDao.update(frontUser); // 更新账户余额

		return "ok";
	}

	@Override
	public PageModel getForPageModel(int pageNum, int pageSize, FootballBetModel model) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("from FootballBet b where 1=1 ");

		if (null != model.getUser() && StringUtils.isNotBlank(model.getUser().getNickname())) {
			queryHql.append(" and b.user.nickname like :nickname");
			params.put("nickname", "%%" + model.getUser().getNickname() + "%%");
		}

		if (StringUtils.isNotBlank(model.getUserId())) {
			queryHql.append(" and b.user.id = :userId");
			params.put("userId", model.getUserId());
		}

		if (StringUtils.isNotBlank(model.getType())) {
			queryHql.append(" and b.type = :type");
			params.put("type", model.getType());
		}

		if (StringUtils.isNotBlank(model.getSeriesNum())) {
			queryHql.append(" and b.seriesNum like :seriesNum");
			params.put("seriesNum", "%%" + model.getSeriesNum() + "%%");
		}

		if (StringUtils.isNotBlank(model.getHomeTeam())) {
			queryHql.append(" and b.homeTeam like :homeTeam");
			params.put("homeTeam", "%%" + model.getHomeTeam() + "%%");
		}

		if (StringUtils.isNotBlank(model.getAwayTeam())) {
			queryHql.append(" and b.awayTeam like :awayTeam");
			params.put("awayTeam", "%%" + model.getAwayTeam() + "%%");
		}

		if (StringUtils.isNotBlank(model.getIsFulfil())) {
			queryHql.append(" and b.isFulfil like :isFulfil");
			params.put("isFulfil", "%%" + model.getIsFulfil() + "%%");
		}

		if (StringUtils.isNotBlank(model.getWinOrLose())) {
			queryHql.append(" and b.winOrLose like :winOrLose");
			params.put("winOrLose", "%%" + model.getWinOrLose() + "%%");
		}

		if (StringUtils.isNotBlank(model.getBetTimeBegin())) {
			queryHql.append(" and b.betTime >= :betTimeBegin");
			params.put("betTimeBegin", model.getBetTimeBegin());
		}

		if (StringUtils.isNotBlank(model.getBetTimeEnd())) {
			queryHql.append(" and b.betTime <= :betTimeEnd");
			params.put("betTimeEnd", model.getBetTimeEnd());
		}

		if (StringUtils.isNotBlank(model.getMatchTimeBegin())) {
			queryHql.append(" and b.matchTime >= :matchTimeBegin");
			params.put("matchTimeBegin", model.getMatchTimeBegin());
		}

		if (StringUtils.isNotBlank(model.getMatchTimeEnd())) {
			queryHql.append(" and b.matchTime <= :matchTimeEnd");
			params.put("matchTimeEnd", model.getMatchTimeEnd());
		}

		if (StringUtils.isNotBlank(model.getMatchType())) {
			queryHql.append(" and b.matchType = :matchType");
			params.put("matchType", model.getMatchType());
		}
		
		if (StringUtils.isNotBlank(model.getIsWinning())) {
			queryHql.append(" and b.isWinning = :isWinning");
			params.put("isWinning", model.getIsWinning());
		}

		queryHql.append(" order by b.isFulfil asc, b.betTime desc"); //

		List<FootballBet> list = baseDao.find(queryHql.toString(), params, pageNum, pageSize);

		List<FootballBetModel> userModels = new ArrayList<FootballBetModel>();
		for (FootballBet u : list) {
			FootballBetModel footballBetModel = new FootballBetModel();
			BeanUtils.copyProperties(u, footballBetModel);
			userModels.add(footballBetModel);
		}

		StringBuffer countHql = new StringBuffer("select count(1) ").append(queryHql);
		int allRows = baseDao.count(countHql.toString(), params).intValue();

		return new PageModel(pageNum, pageSize, list, allRows);
	}

	@Override
	public void deleteFootballBet(String id) {

		String[] ids = id.split(",");

		for (String sigleId : ids) {
			FootballBet footballBet = baseDao.get(FootballBet.class, sigleId);

			baseDao.delete(footballBet);
		}

	}

	@Override
	public void updateFulfilStatus(String id) {

		String[] ids = id.split(",");

		for (String sigleId : ids) {
			FootballBet footballBet = baseDao.get(FootballBet.class, sigleId);

			footballBet.setIsFulfil("yes");

			baseDao.update(footballBet);
		}

	}

	@Override
	public Double countBonusBySeriesNum(String seriesNum) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seriesNum", seriesNum);

		List<FootballBet> list = baseDao.find("from FootballBet b where b.seriesNum = :seriesNum", params);

		double bonus = 0;

		if (null == list || list.size() == 0) {
			return bonus;
		}

		double totalOdds = 1;
		int multiple = 0;

		for (FootballBet bean : list) {
			double odds = bean.getOdds();
			totalOdds = totalOdds * odds;
			multiple = bean.getMultiple();
		}

		bonus = totalOdds * 2 * multiple;
		return bonus;
	}

	@Override
	public void updateFulfilBySeriesNum(String seriesNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seriesNum", seriesNum);

		List<FootballBet> list = baseDao.find("from FootballBet b where b.seriesNum = :seriesNum", params);

		for (FootballBet bean : list) {

			bean.setIsFulfil("yes");

			baseDao.update(bean);
		}
	}

	@Override
	public List<FootballBetModel> getNotFulfilFootballBet() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isWinning", "no");

		List<FootballBet> list = baseDao.find("from FootballBet b where b.isWinning = :isWinning", params);

		List<FootballBetModel> modelList = new ArrayList<FootballBetModel>();

		FootballBetModel model = null;

		for (FootballBet bean : list) {
			model = new FootballBetModel();

			BeanUtils.copyProperties(bean, model);

			modelList.add(model);

		}

		return modelList;
	}

	@Override
	public List<FootballBetModel> getFootballBetBySeriesNum(String seriesNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seriesNum", seriesNum);

		List<FootballBet> list = baseDao.find("from FootballBet b where b.seriesNum = :seriesNum", params);

		List<FootballBetModel> modelList = new ArrayList<FootballBetModel>();

		FootballBetModel model = null;

		for (FootballBet bean : list) {
			model = new FootballBetModel();

			BeanUtils.copyProperties(bean, model);

			modelList.add(model);
		}

		return modelList;
	}

	@Override
	public void updateFootballBetWinningBySeriesNum(String isWinning, String seriesNum) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seriesNum", seriesNum);

		List<FootballBet> list = baseDao.find("from FootballBet b where b.seriesNum = :seriesNum", params);

		if ("true" == isWinning) {
			
			double bonus = 0;

			double totalOdds = 1;
			int multiple = 0;

			for (FootballBet bean : list) {
				double odds = bean.getOdds();
				totalOdds = totalOdds * odds;
				multiple = bean.getMultiple();
			}

			bonus = totalOdds * 2 * multiple;
			
			FrontUser frontUser = frontUserDao.get(FrontUser.class, list.get(0).getUser().getId());
			
			double balance = frontUser.getBalance() + bonus;
			
			frontUser.setBalance(balance);
			
			frontUserDao.update(frontUser);

			for (FootballBet bean : list) {

				bean.setIsWinning("true");
				bean.setIsFulfil("yes");

				baseDao.update(bean);
			}
		} else {
			for (FootballBet bean : list) {

				bean.setIsWinning("false");
				bean.setIsFulfil("yes");

				baseDao.update(bean);
			}

		}

	}

	@Override
	public void updateFootballBetWinningById(String isWinning, String id) {

		FootballBet bean = baseDao.get(FootballBet.class, id);

		if ("true".equals(isWinning)) {
			
			FrontUser frontUser = frontUserDao.get(FrontUser.class, bean.getUser().getId());
			
			double bonus = bean.getOdds() * bean.getMultiple() * 2;
			
			double balance = frontUser.getBalance() + bonus;
			
			frontUser.setBalance(balance);
			
			frontUserDao.update(frontUser);
			
			bean.setIsWinning("true");
			bean.setIsFulfil("yes");
		} else {
			bean.setIsWinning("false");
			bean.setIsFulfil("yes");
		}

		baseDao.update(bean);
	}

}
