package com.vn.hcmute.team.cortana.mymoney.bean;

public class Saving {
	private String savingid;
	public String getSavingid() {
		return savingid;
	}
	public void setSavingid(String savingid) {
		this.savingid = savingid;
	}
	private String name;
	private String goalMoney;
	private String startMoney;
	private String currentMoney;
	
	private String date;
	private String idWallet;
	private String idCurrencies;
	private String status;
	private String userid;
	
	public Saving() {
		this.savingid="";
		this.name="";
		this.goalMoney="";
		this.startMoney="";
		this.date="";
		this.idWallet="";
		this.idCurrencies="";
		this.status="";
		this.userid="";
		
	}
	public String getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(String currentMoney) {
		this.currentMoney = currentMoney;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGoalMoney() {
		return goalMoney;
	}
	public void setGoalMoney(String goalMoney) {
		this.goalMoney = goalMoney;
	}
	public String getStartMoney() {
		return startMoney;
	}
	public void setStartMoney(String startMoney) {
		this.startMoney = startMoney;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIdWallet() {
		return idWallet;
	}
	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}
	public String getIdCurrencies() {
		return idCurrencies;
	}
	public void setIdCurrencies(String idCurrencies) {
		this.idCurrencies = idCurrencies;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
