package com.vn.hcmute.team.cortana.mymoney.bean;

public class Person {
	private String personid;
	private String name;
	private String describe;
	private String userid;
	public Person() {
		this.personid="";
		this.name="";
		this.describe="";
		this.userid="";
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personid == null) {
			if (other.personid != null)
				return false;
		} else if (!personid.equals(other.personid))
			return false;
		return true;
	}
	
}
