package com.vn.hcmute.team.cortana.mymoney.bean;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String cate_id;
	private String name;
	private String icon;
	private String trans_type; // 0 - Imcoming,1 - expense, 2 - debt&loan
	private String type; // 0 - incoming, 1 -expense
	List<Category> subcategories;
	private String user_id;

	public Category() {
		cate_id = "";
		name = "";
		icon = "";
		trans_type="";
		type="";
		subcategories= new ArrayList<Category>();
		user_id="";
	}
	public Category(String id, String name,String icon, String transType, String type) {
        this.cate_id = id;
        this.icon = icon;
        this.name = name;
        this.trans_type = transType;
        this.type = type;
    }

	public Category(String id, String name, String icon, String trans_type, String type,
			List<Category> subcategories, String userid) {
		this.cate_id = id;
		this.name = name;
		this.icon = icon;
		this.trans_type = trans_type;
		this.type = type;
		this.subcategories = subcategories;
		this.user_id = userid;
	}

	public String getCate_id() {
		return cate_id;
	}

	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}

	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cate_id == null) ? 0 : cate_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (cate_id == null) {
			if (other.cate_id != null)
				return false;
		} else if (!cate_id.equals(other.cate_id))
			return false;
		return true;
	}
	

	
}
