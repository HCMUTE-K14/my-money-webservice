package com.vn.hcmute.team.cortana.mymoney.bean;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 5217094060448008597L;
	
	public static int TYPE_DEFAULT=0;
	public static int TYPE_CUSTOM=1;
	
	private String categoryId;		
	private String categoryName;
	private int categoryType; 		//0:default, 1:custom
	private String categoryImage;
	private String categoryParent; 	//0:default(Not have child); #0: categoryId parent 
	private String userId; 			
	
	public Category(){
		this.categoryId="-1";
		this.categoryImage="";
		this.categoryType=0;
		this.categoryImage="";
		this.categoryParent="0";
		this.userId=User.DEFAULT_USER_ID;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(String categoryParent) {
		this.categoryParent = categoryParent;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryType="
				+ categoryType + ", categoryImage=" + categoryImage + ", categoryParent=" + categoryParent + ", userId="
				+ userId + "]";
	}

	

	
	
}
