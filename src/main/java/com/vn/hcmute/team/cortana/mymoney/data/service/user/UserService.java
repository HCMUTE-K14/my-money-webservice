package com.vn.hcmute.team.cortana.mymoney.data.service.user;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;

public interface UserService {
	void register(User user) throws UserException;
	
	long getNumberOfActiveUser();
	
	boolean isUserExists(String username);
	
	User login(UserCredential userCredential) throws UserException;
}
