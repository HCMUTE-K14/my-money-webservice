package com.vn.hcmute.team.cortana.mymoney.utils;

import java.util.HashMap;
import java.util.Map;

public class AllowToken {
    
    protected static AllowToken ins = new AllowToken();
    private Map<String, String> mMap;
    
    private AllowToken() {
        this.mMap = new HashMap<>();
    }
    
    public static AllowToken getInstance() {
        return ins;
    }
    
    public boolean isAccessToken(String userid, String token) {
        String tok = mMap.get(userid);
        if (TextUtil.isEmpty(tok)) {
            return false;
        }
        return tok.equals(token);
    }
    
    public void putToken(String userid, String token) {
        mMap.put(userid, token);
    }
    
    public void removeToken(String userid) {
        mMap.remove(userid);
    }
}
