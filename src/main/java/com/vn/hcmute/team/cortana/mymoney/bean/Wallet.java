package com.vn.hcmute.team.cortana.mymoney.bean;

public class Wallet {
	private String wallet_id;
	private String user_id;
	private String name;
	private String money;
	private Currencies currency;
	private String icon;
	private boolean archive;
	
	public Wallet(){
		this.wallet_id="";
		this.user_id  = "";
		this.name = "";
		this.money = "";
		this.currency = null;
		this.icon = "";
		this.archive = false;
	}
	
	public String getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Currencies getCurrency() {
		return currency;
	}
	public void setCurrency(Currencies currency) {
		this.currency = currency;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	

	
	
}
