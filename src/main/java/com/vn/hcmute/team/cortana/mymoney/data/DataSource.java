package com.vn.hcmute.team.cortana.mymoney.data;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;

public class DataSource {
	public interface UserDataSource {
		void register(User user);

		long getNumberOfActiveUser();
		
		User login(UserCredential userCredential);
		
		boolean isApiKey(String userid,String token);
	}
	public interface WalletDataSource{
	
	}
	public interface CategorySource{
		List<Category> getDefaultCategory(String userid);
		
		List<Category> getCategoryByUserId(String userid);
		
		void addCategory(Category category);
		
		void updateCategory(Category category);
		
		void removeCategory(Category category);
		
	}
}
