package com.vn.hcmute.team.cortana.mymoney.bean;

import java.io.Serializable;
import java.util.List;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 9179906347617231970L;
	
	private String trans_id;
	private double amount;
	private List<Person> person;
	private String address;
	private String note;
	private List<Image> image;
	private int type; //0:Cho vay, 1:Chit ieu ,2 thu nhap
	private String cate_id;
	private String event_id;
	private String latitude;
	private String longtitude;
	private String user_id; 
	private String wallet_id;
	private long date_created;
	private long date_end;
	private String saving_id;
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<Person> getPerson() {
		return person;
	}
	public void setPerson(List<Person> person) {
		this.person = person;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Image> getImage() {
		return image;
	}
	public void setImage(List<Image> image) {
		this.image = image;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}
	public long getDate_created() {
		return date_created;
	}
	public void setDate_created(long date_created) {
		this.date_created = date_created;
	}
	public long getDate_end() {
		return date_end;
	}
	public void setDate_end(long date_end) {
		this.date_end = date_end;
	}
	public String getSaving_id() {
		return saving_id;
	}
	public void setSaving_id(String saving_id) {
		this.saving_id = saving_id;
	}
	
	
}
