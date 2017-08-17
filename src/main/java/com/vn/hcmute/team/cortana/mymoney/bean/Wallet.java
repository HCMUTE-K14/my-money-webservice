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
	private String walletImage;
	public String getWalletImage() {
		return walletImage;
	}
	public void setWalletImage(String walletImage) {
		this.walletImage = walletImage;
	}
	public Wallet() {
		this.walletid="";
		this.userid="";
		this.walletName="";
		this.money="";
		this.currencyUnit="";
	}
	public Wallet(String id, String userid,String walletName,String money,String currencyUnit,String token) {
		this.walletid=id;
		this.userid=userid;
		this.walletName=walletName;
		this.money=money;
		this.currencyUnit=currencyUnit;
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
