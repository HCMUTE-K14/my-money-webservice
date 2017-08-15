package com.vn.hcmute.team.cortana.mymoney.utils;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Template;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
@Component
public class SecurityUtil {
	@Autowired
	MongoTemplate mongoTemplate;
	
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
	public  String getApiKey(String userid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userid").is(userid));
		User user = mongoTemplate.findOne(query, User.class,DbConstraint.TABLE_USER);
		
		return user.getApikey();
	}

}
