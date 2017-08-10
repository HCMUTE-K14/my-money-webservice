package com.vn.hcmute.team.cortana.mymoney.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.data.service.user.UserService;

@Repository
public class DataRepository implements DataSource.UserDataSource{
	
	public static final Log LOG=LogFactory.getLog(DataRepository.class);
	
	private UserService mUserService;
	
	@Autowired
	public DataRepository( UserService userService){
		this.mUserService=userService;
		LOG.info("DataRepository is created");
	}

	@Override
	public void register(User user) {
		this.mUserService.register(user);
		
	}

	@Override
	public long getNumberOfActiveUser() {
		return this.mUserService.getNumberOfActiveUser();
	}

	@Override
	public User login(UserCredential userCredential) {
		return this.mUserService.login(userCredential);
	}

	
	
}
