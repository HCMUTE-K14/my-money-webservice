package com.vn.hcmute.team.cortana.mymoney.utils;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
	@Autowired
	MongoTemplate mongoTemplate;

	private static String SECRET_KEY = "hpXhbhYA5N";

	public static String generateUUID() {

		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String generateMD5(String input) {
		return DigestUtils.md5Hex(input);
	}

	public static String generateApiKey(String token) {
		return DigestUtils.sha256Hex(SECRET_KEY + token);
	}

	public static String generatePassword() {
		return String.valueOf(new Random().nextInt(900000) + 100000);
	}



}
