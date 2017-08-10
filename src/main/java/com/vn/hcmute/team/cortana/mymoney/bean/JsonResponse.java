package com.vn.hcmute.team.cortana.mymoney.bean;

import com.google.gson.JsonObject;

public class JsonResponse {
	private String status;
	private String message;
	private JsonObject data;
	
	
	public JsonResponse(){
		this.status="";
		this.message="";
		this.data=null;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public JsonObject getData() {
		return data;
	}


	public void setData(JsonObject data) {
		this.data = data;
	}

}
