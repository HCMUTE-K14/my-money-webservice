package com.vn.hcmute.team.cortana.mymoney.bean;

public class Image {
	private String imageId;
	private String imageUrl;
	private String userId;
	private String imageDetail;
	
	public Image() {
		this.imageId = "";
		this.imageUrl = "";
		this.userId = "";
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getImageDetail() {
		return imageDetail;
	}

	public void setImageDetail(String imageDetail) {
		this.imageDetail = imageDetail;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageUrl=" + imageUrl + ", userId=" + userId + ", imageDetail="
				+ imageDetail + "]";
	}

}
