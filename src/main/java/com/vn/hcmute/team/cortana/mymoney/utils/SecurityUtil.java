package com.vn.hcmute.team.cortana.mymoney.utils;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {
	
	private static String SECRET_KEY="hpXhbhYA5N";

	public static String generateUUID() {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static String generateMD5(String input){
		return DigestUtils.md5Hex(input);
	}
	
	public static String generateApiKey(String token){
		return DigestUtils.sha256Hex(SECRET_KEY+token);
	}
}
