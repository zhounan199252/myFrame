package com.gzhd.model;

public class BetMessageModel {

	private String id;

	private String betPeriod; // 投注期数
	private String betType; // 投注类型
	private String   betChildType;   //投注次类型
	private String betPerson; // 投注人id
	private String betQuan; // 投注数量
	private String betNum; // 投注号码
	private String exchangeFlag; // 兑换标志
	private String betDate; // 投注日期

	private String betPersonName; // 投注人名字
	private double betPrice = 2; // 投注数量
	private int pageNum = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBetPeriod() {
		return betPeriod;
	}

	public void setBetPeriod(String betPeriod) {
		this.betPeriod = betPeriod;
	}

	public String getBetPerson() {
		return betPerson;
	}

	public void setBetPerson(String betPerson) {
		this.betPerson = betPerson;
	}

	public String getBetQuan() {
		return betQuan;
	}

	public void setBetQuan(String betQuan) {
		this.betQuan = betQuan;
	}

	public String getBetNum() {
		return betNum;
	}

	public void setBetNum(String betNum) {
		this.betNum = betNum;
	}

	public String getBetDate() {
		return betDate;
	}

	public void setBetDate(String betDate) {
		this.betDate = betDate;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getBetPersonName() {
		return betPersonName;
	}

	public void setBetPersonName(String betPersonName) {
		this.betPersonName = betPersonName;
	}

	public double getBetPrice() {
		return betPrice;
	}

	public void setBetPrice(double betPrice) {
		this.betPrice = betPrice;
	}

	public String getExchangeFlag() {
		return exchangeFlag;
	}

	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}

	public String getBetChildType() {
		return betChildType;
	}

	public void setBetChildType(String betChildType) {
		this.betChildType = betChildType;
	}
	
	

}
