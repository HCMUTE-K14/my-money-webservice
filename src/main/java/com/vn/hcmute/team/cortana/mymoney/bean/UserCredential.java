package com.vn.hcmute.team.cortana.mymoney.bean;

import java.io.Serializable;

public class UserCredential implements Serializable {

    private static final long serialVersionUID = 6410053855871985720L;

    private String username;
    private String password;

    public UserCredential() {
        this.username = "";
        this.password = "";
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

}
