package com.vn.hcmute.team.cortana.mymoney.bean;

public class Image {

    private String image_id;
    private String url;
    private String user_id;
    private String detail;

    public Image() {
        this.image_id = "";
        this.url = "";
        this.user_id = "";
        this.detail = "";
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Image [image_id=" + image_id + ", url=" + url + ", user_id=" + user_id +
               ", detail=" + detail + "]";
    }

}
