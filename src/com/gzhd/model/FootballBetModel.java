package com.gzhd.model;

import com.gzhd.domain.FrontUser;

public class FootballBetModel {

	private String id;

	private String data;

	private FrontUser user;

	private String betTime;

	private String homeTeam;

	private String awayTeam;

	private double odds;

	private String rangQiu;

	private String winOrLose;

	private int multiple;
	
	private double totalCount;
	
	private String isFulfil;
	
	private String matchTime;
	
	private String betTimeBegin;
	
	private String betTimeEnd;
	
	private String matchTimeBegin;
	
	private String matchTimeEnd;
	
	private String message;
	
	private String type;
	
	private String seriesNum;
	
	private String matchType;
	
	private String userId;
	
	private String matchId;
	
	private String isWinning;
	
	private int pageNum = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public FrontUser getUser() {
		return user;
	}

	public void setUser(FrontUser user) {
		this.user = user;
	}

	public String getBetTime() {
		return betTime;
	}

	public void setBetTime(String betTime) {
		this.betTime = betTime;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getRangQiu() {
		return rangQiu;
	}

	public void setRangQiu(String rangQiu) {
		this.rangQiu = rangQiu;
	}

	public String getWinOrLose() {
		return winOrLose;
	}

	public void setWinOrLose(String winOrLose) {
		this.winOrLose = winOrLose;
	}

	public int getMultiple() {
		return multiple;
	}

	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}

	public double getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getIsFulfil() {
		return isFulfil;
	}

	public void setIsFulfil(String isFulfil) {
		this.isFulfil = isFulfil;
	}

	public double getOdds() {
		return odds;
	}

	public void setOdds(double odds) {
		this.odds = odds;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public String getBetTimeBegin() {
		return betTimeBegin;
	}

	public void setBetTimeBegin(String betTimeBegin) {
		this.betTimeBegin = betTimeBegin;
	}

	public String getBetTimeEnd() {
		return betTimeEnd;
	}

	public void setBetTimeEnd(String betTimeEnd) {
		this.betTimeEnd = betTimeEnd;
	}

	public String getMatchTimeBegin() {
		return matchTimeBegin;
	}

	public void setMatchTimeBegin(String matchTimeBegin) {
		this.matchTimeBegin = matchTimeBegin;
	}

	public String getMatchTimeEnd() {
		return matchTimeEnd;
	}

	public void setMatchTimeEnd(String matchTimeEnd) {
		this.matchTimeEnd = matchTimeEnd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeriesNum() {
		return seriesNum;
	}

	public void setSeriesNum(String seriesNum) {
		this.seriesNum = seriesNum;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getIsWinning() {
		return isWinning;
	}

	public void setIsWinning(String isWinning) {
		this.isWinning = isWinning;
	}
	
	
}
