package com.vn.hcmute.team.cortana.mymoney.bean;

public class Converter {
	
	private String amount;
	private String from;
	private String to;
	private String value;
	
	public Converter(){
		this.amount="0";
		this.from="";
		this.to="";
		this.value="";
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Converter [amount=" + amount + ", from=" + from + ", to=" + to + ", value=" + value + "]";
	}

	
}
