package com.gzhd.service.itf;

import java.util.List;

import com.gzhd.model.FootballBetModel;
import com.gzhd.model.PageModel;

public interface FootballBetService {

	public static final String BEAN_NAME = "com.gzhd.service.itf.FootballBetService";
	
	public String addFootballBet(FootballBetModel model, String userId, String curTime);
	
	public PageModel getForPageModel(int pageNum, int pageSize, FootballBetModel model);
	
	public void deleteFootballBet(String ids);
	
	public void updateFulfilStatus(String ids);

	public Double countBonusBySeriesNum(String seriesNum);

	public void updateFulfilBySeriesNum(String seriesNum);
	
	public List<FootballBetModel> getNotFulfilFootballBet();
	
	public List<FootballBetModel> getFootballBetBySeriesNum(String seriesNum);

	public void updateFootballBetWinningBySeriesNum(String isWinning, String seriesNum);

	public void updateFootballBetWinningById(String isWinning, String id);
}
