package com.vn.hcmute.team.cortana.mymoney.bean;

import java.io.Serializable;
import java.util.List;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 9179906347617231970L;
	
	private String transactionId;
	private double amount;
	private String currencyId;
	private List<Person> person;
	private String address;
	private String note;
	private List<Image> image;
	private int type; //0:Cho vay, 1:Chit ieu ,2 thu nhap
	private String categoryId;
	private String eventId;
	private String latitude;
	private String longtitude;
	private String userId; 
	private String walletId;
	private long dateCreate;
	private long dateEnd;
	
	public Transaction(){
		
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public long getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(long dateCreate) {
		this.dateCreate = dateCreate;
	}

	public long getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(long dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", currencyId=" + currencyId
				+ ", person=" + person + ", address=" + address + ", note=" + note + ", image=" + image + ", type="
				+ type + ", categoryId=" + categoryId + ", eventId=" + eventId + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + ", userId=" + userId + ", walletId=" + walletId + ", dateCreate="
				+ dateCreate + ", dateEnd=" + dateEnd + "]";
	}
	
	
	
	
}
