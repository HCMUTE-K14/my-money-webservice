package com.vn.hcmute.team.cortana.mymoney.bean;

public class Event {
	private String id;
	private String name;
	private String money;
	private String date;
	private String idWallet;
	//Đánh dấu trạng thái kết thúc sự kiện hay chưa
	private String status;
	private String userid;
	private String token;
	public Event() {
		this.id="";
		this.name="";
		this.money="";
		this.date="";
		this.idWallet="";
		this.status="";
		this.userid="";
		this.token="";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
