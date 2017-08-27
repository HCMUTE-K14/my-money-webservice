package com.vn.hcmute.team.cortana.mymoney.bean;

public class Event {
	private String eventid;
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	private String name;
	private String money;
	private String date;
	private String idWallet;
	//Đánh dấu trạng thái kết thúc sự kiện hay chưa
	private String status;
	private String userid;
	public Event() {
		this.eventid="";
		this.name="";
		this.money="";
		this.date="";
		this.idWallet="";
		this.status="";
		this.userid="";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventid == null) {
			if (other.eventid != null)
				return false;
		} else if (!eventid.equals(other.eventid))
			return false;
		return true;
	}
	
}
