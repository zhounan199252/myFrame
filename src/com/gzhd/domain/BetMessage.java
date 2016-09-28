package com.gzhd.domain;


public class BetMessage {
	
	private String id;
	private String betPeriod;  //投注期数
	private String   betType;   //投注类型
	private String   betChildType;   //投注次类型
	private String betPerson;   //投注人	
	private String betQuan;    //投注数量
	private String betNum;   //投注号码
	private String exchangeFlag;    //兑换标志
	private String betDate; // 投注日期
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
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public String getBetChildType() {
		return betChildType;
	}
	public void setBetChildType(String betChildType) {
		this.betChildType = betChildType;
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
	public String getExchangeFlag() {
		return exchangeFlag;
	}
	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}
	public String getBetDate() {
		return betDate;
	}
	public void setBetDate(String betDate) {
		this.betDate = betDate;
	}

		
}
