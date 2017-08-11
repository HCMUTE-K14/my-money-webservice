package com.vn.hcmute.team.cortana.mymoney.data;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;

public class DataSource {
	public interface UserDataSource {
		void register(User user);

		long getNumberOfActiveUser();
		
		User login(UserCredential userCredential);
	}
}
