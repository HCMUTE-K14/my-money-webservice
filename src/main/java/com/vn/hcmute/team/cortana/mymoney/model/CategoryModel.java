package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.CategoryException;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;

@Component
public class CategoryModel {

	private DataRepository mDataRepository;

	@Autowired
	public CategoryModel(DataRepository dataRepository) {
		mDataRepository = dataRepository;
	}

	public void getDefaultCategory(CallBack<List<Category>> callback) {
		try {
			List<Category> result = mDataRepository.getDefaultCategory();
			if (result != null) {
				callback.onSuccess(result);
			}
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	public void getCategoryByUserId(String userid, String token, CallBack<List<Category>> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Category> result = mDataRepository.getCategoryByUserId(userid);
			if (result != null) {
				callback.onSuccess(result);
			}
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	public void addCategory(String userid, String token, Category category, CallBack<String> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			if(category.getCategoryType()!=Category.TYPE_DEFAULT && !category.getUserId().equals(userid)){
				callback.onFailure(new CategoryException("Category userid have different userid param"));
				return;
			}
			mDataRepository.addCategory(category);
			callback.onSuccess("Add successful");
		} catch (CategoryException e) {
			callback.onFailure(e);
		}
	}

	public void updateCategory(String userid, String token, Category category, CallBack<String> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			if(category.getCategoryType()!=Category.TYPE_DEFAULT && !category.getUserId().equals(userid)){
				callback.onFailure(new CategoryException("Category userid have different userid param"));
				return;
			}
			mDataRepository.updateCategory(category);
			callback.onSuccess("Update successful");
		} catch (Exception e) {
			callback.onFailure(e);
		}

	}

	public void removeCategory(String userid, String token, Category category, CallBack<String> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			if(category.getCategoryType()!=Category.TYPE_DEFAULT && !category.getUserId().equals(userid)){
				callback.onFailure(new CategoryException("Category userid have different userid param"));
				return;
			}
			mDataRepository.removeCategory(category);
			callback.onSuccess("Remove successful");
		} catch (Exception e) {
			callback.onFailure(e);
		}

	}

}
