package com.vn.hcmute.team.cortana.mymoney.data;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.data.service.category.CategoryService;
import com.vn.hcmute.team.cortana.mymoney.data.service.user.UserService;

@Repository
public class DataRepository implements DataSource.UserDataSource,DataSource.CategorySource{
	
	public static final Log LOG=LogFactory.getLog(DataRepository.class);
	
	@Autowired
	private UserService mUserService;
	
	@Autowired
	private CategoryService mCategoryService;
	
	public DataRepository(){
		
	}

	@Override
	public void register(User user)  {
		this.mUserService.register(user);
	}

	@Override
	public long getNumberOfActiveUser() {
		return this.mUserService.getNumberOfActiveUser();
	}

	@Override
	public User login(UserCredential userCredential)  {
		return this.mUserService.login(userCredential);
	}

	@Override
	public List<Category> getDefaultCategory(String userid) {
		return mCategoryService.getDefaultCategory(userid);
	}

	@Override
	public List<Category> getCategoryByUserId(String userid) {
		return mCategoryService.getCategoryByUserId(userid);
	}

	@Override
	public void addCategory(Category category) {
		mCategoryService.addCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		mCategoryService.updateCategory(category);
	}

	@Override
	public void removeCategory(Category category) {
		mCategoryService.removeCategory(category);
	}

	@Override
	public boolean isApiKey(String userid, String token) {
		return mUserService.isApiKey(userid, token);
	}
}
