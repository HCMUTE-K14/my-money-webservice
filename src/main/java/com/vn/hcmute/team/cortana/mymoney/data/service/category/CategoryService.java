package com.vn.hcmute.team.cortana.mymoney.data.service.category;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;

public interface CategoryService {
	
	List<Category> getCategoryByUserId(String userid)throws RuntimeException;
	
	List<Category> getCategoryByTransactionType(String userid,String transactionType) throws RuntimeException;
	
	List<Category> getCategoryByType(String userid,String type,String transType) throws RuntimeException;
	
	void addCategory(Category category,String parentId)throws RuntimeException;
	
	void updateCategory(Category category,String oldParentId,String newParentId)throws RuntimeException;
	
	void removeCategory(Category category,String parentId)throws RuntimeException;
	
	boolean isExistsCategory(Category category)throws RuntimeException;
	
	void initDefaultCategory(String userid) throws RuntimeException;
	
	void syncCategory(List<Category> categories) throws RuntimeException;
}
