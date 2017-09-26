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
			System.out.println(result.get(0));
			if (result != null) {
				callback.onSuccess(result);
			}
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}
	
	public void getCategoryByTransactionType(String userid,String token,String transactionType,CallBack<List<Category>> callback){
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Category> result = mDataRepository.getCategoryByTransactionType(userid, transactionType);

			if (result != null) {
				callback.onSuccess(result);
			}
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}
	
	public void getCategoryByType(String userid,String token,String type,String transType,CallBack<List<Category>> callback){
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Category> result = mDataRepository.getCategoryByType(userid, type,transType);

			if (result != null) {
				callback.onSuccess(result);
			}
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	public void addCategory(String userid, String token, Category category, String parentId,CallBack<String> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			if(!category.getUser_id().equals(userid)){
				callback.onFailure(new CategoryException("Category userid have different userid param"));
				return;
			}
			mDataRepository.addCategory(category,parentId);
			callback.onSuccess("Add successful");
		} catch (CategoryException e) {
			callback.onFailure(e);
		}
	}

	public void updateCategory(String userid, String token, Category category,String oldParentId,String newParentId, CallBack<String> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			if(!category.getUser_id().equals(userid)){
				callback.onFailure(new CategoryException("Category userid have different userid param"));
				return;
			}
			mDataRepository.updateCategory(category,oldParentId,newParentId);
			callback.onSuccess("Update successful");
		} catch (Exception e) {
			callback.onFailure(e);
		}

	}

	public void removeCategory(String userid, String token, Category category,String parentId, CallBack<String> callback) {
		try {
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			if(!category.getUser_id().equals(userid)){
				callback.onFailure(new CategoryException("Category userid have different userid param"));
				return;
			}
			mDataRepository.removeCategory(category,parentId);
			callback.onSuccess("Remove successful");
		} catch (Exception e) {
			callback.onFailure(e);
		}

	}

}
