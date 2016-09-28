package com.gzhd.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gzhd.dao.itf.BaseDao;
import com.gzhd.domain.MatchResult;
import com.gzhd.model.MatchResultModel;
import com.gzhd.service.itf.MatchResultService;
import com.gzhd.util.TimeUtil;

@Service(value = MatchResultService.BEAN_NAME)
public class MatchResultServiceImpl implements MatchResultService {

	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<MatchResult> baseDao;

	@Override
	public String addMatchResult(MatchResultModel model) {

		MatchResult bean = new MatchResult();

		BeanUtils.copyProperties(model, bean);

		Serializable res = baseDao.save(bean);

		return (String) res;
	}

	@Override
	public boolean checkResultIsExistByMatchId(String matchId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("matchId", matchId);
		
		List<MatchResult> list = baseDao.find("from MatchResult m where m.matchId = :matchId", params);
		
		if(list.size() == 0) {
			return false;
		}
		
		return true;
	}

	@Override
	public Map<String, String> getMatchResultByDate() {
		
		Date dateRelateToDate = TimeUtil.getDateRelateToDate(new Date(), -1);

		String lastDay = TimeUtil.dateFormat(dateRelateToDate, "yyyy-MM-dd HH:mm:ss");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("matchTime", lastDay);
		
		List<MatchResult> list = baseDao.find("from MatchResult m where m.matchTime >= :matchTime", params);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		for (MatchResult bean : list) {
			
			resultMap.put(bean.getMatchId(), bean.getWinOrLose());
			
		}
		
		return resultMap;
	}
	
	
	
	

}
