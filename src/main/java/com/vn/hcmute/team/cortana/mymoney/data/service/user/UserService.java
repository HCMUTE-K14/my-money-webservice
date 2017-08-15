package com.vn.hcmute.team.cortana.mymoney.data.service.user;

import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;

public interface UserService {
	void register(User user) throws UserException;
	
	long getNumberOfActiveUser();
	
	boolean isUserExists(String username);
	
	User login(UserCredential userCredential) throws UserException;
}
