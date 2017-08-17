package com.vn.hcmute.team.cortana.mymoney.bean;

public class Wallet {
	private String walletid;
	public String getWalletid() {
		return walletid;
	}
	public void setWalletid(String walletid) {
		this.walletid = walletid;
	}

	private String userid;
	private String walletName;
	private String money;
	private String currencyUnit;
	public Wallet() {
		this.walletid="";
		this.userid="";
		this.walletName="";
		this.money="";
		this.currencyUnit="";
	
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWalletName() {
		return walletName;
	}
	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCurrencyUnit() {
		return currencyUnit;
	}
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	
	@Override
	public String toString() {
		return "Wallet[id= "+walletid+",userid= "+userid+",walletname="+
				walletName+",money="+money+",curencyUnit="+currencyUnit+"]";
	}
	
	
}
