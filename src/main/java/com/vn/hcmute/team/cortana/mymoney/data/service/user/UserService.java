package com.vn.hcmute.team.cortana.mymoney.data.service.user;

import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;

public interface UserService {
	void register(User user);
	
	long getNumberOfActiveUser();
	
	boolean isUserExists(String username);
	
	User login(UserCredential userCredential) ;
	
	boolean isApiKey(String userid,String token);
	
	void forgetPassword(String email);
}
