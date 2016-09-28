package com.gzhd.domain;

public class DepositApply {
	
	private String id;
	private String   userId;  //申请人
	private String   money;  //金额
	private String   bankNum;  //银行卡号
	private String   applyTime;   //申请时间
	
	private String   status;      //状态
	private String   handletime;   //处理时间	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHandletime() {
		return handletime;
	}
	public void setHandletime(String handletime) {
		this.handletime = handletime;
	}
	
	
	
	
	
	
	
	
	

}
