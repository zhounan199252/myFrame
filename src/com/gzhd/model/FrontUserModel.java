package com.gzhd.model;

public class FrontUserModel {

	private String id;
	
	private String nickname;
	
	private String username;
	
	private String password;
	
	private String idCardNum;
	
	private String phoneNum;
	
	private String registerTime;
	
	private String lastLoginTime;
	
	private String code;
	
	private String registerTimeBegin;
	
	private String registerTimeEnd;
	
	private String lastLoginTimeBegin;
	
	private String lastLoginTimeEnd;
	
	private double balance;
	
	private double balanceBegin;
	
	private double balanceEnd;
	
	private String recommender;
	
	private int pageNum = 1; // 一定要有默认值1，不然会报错
	
	private String isValid;
	
	private double consumption;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getRegisterTimeBegin() {
		return registerTimeBegin;
	}

	public void setRegisterTimeBegin(String registerTimeBegin) {
		this.registerTimeBegin = registerTimeBegin;
	}

	public String getRegisterTimeEnd() {
		return registerTimeEnd;
	}

	public void setRegisterTimeEnd(String registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}

	public String getLastLoginTimeBegin() {
		return lastLoginTimeBegin;
	}

	public void setLastLoginTimeBegin(String lastLoginTimeBegin) {
		this.lastLoginTimeBegin = lastLoginTimeBegin;
	}

	public String getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}

	public void setLastLoginTimeEnd(String lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalanceBegin() {
		return balanceBegin;
	}

	public void setBalanceBegin(double balanceBegin) {
		this.balanceBegin = balanceBegin;
	}

	public double getBalanceEnd() {
		return balanceEnd;
	}

	public void setBalanceEnd(double balanceEnd) {
		this.balanceEnd = balanceEnd;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	
}
