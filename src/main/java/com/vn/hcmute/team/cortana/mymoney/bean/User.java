package com.vn.hcmute.team.cortana.mymoney.bean;

import java.io.Serializable;

public class User implements Serializable {

    public static final String DEFAULT_USER_ID = "ALL";
    private static final long serialVersionUID = -1530136052354836173L;
    private String user_id;
    private String username;
    private String password;
    private String name;
    private String token;
    private String apikey;
    private String email;
    private boolean active;
    private String facebook_id;

    public User() {
        this.user_id = "DEFAULT";
        this.username = "";
        this.password = "";
        this.name = "";
        this.token = "";
        this.apikey = "";
        this.email = "";
        this.active = false;
        this.facebook_id = "";
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [userid=" + user_id + ", username=" + username + ", password=" + password +
               ", name=" + name
               + ", token=" + token + ", apikey=" + apikey + ", email=" + email + ", active=" +
               active + "]";
    }

}
