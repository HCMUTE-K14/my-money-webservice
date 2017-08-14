package com.vn.hcmute.team.cortana.mymoney.data.service.user;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;

public interface UserService {
	void register(User user);
	
	long getNumberOfActiveUser();
	
	boolean isUserExists(String username);
	
	User login(UserCredential userCredential) ;
	
	boolean isApiKey(String userid,String token);
}
