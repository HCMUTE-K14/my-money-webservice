package com.vn.hcmute.team.cortana.mymoney.bean;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -1530136052354836173L;
	
	private String userid;
	private String username;
	private String password;
	private String name;
	private String token;
	private String apikey;
	private boolean active;
	
	public User(){
		this.userid="";
		this.username="";
		this.password="";
		this.name="";
		this.token="";
		this.apikey="";
		this.active=false;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", token=" + token + "]";
	}
	
}
