package com.vn.hcmute.team.cortana.mymoney.utils;

public class TextUtil {
    
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.isEmpty()) {
            return true;
        }
        return false;
    }
}
