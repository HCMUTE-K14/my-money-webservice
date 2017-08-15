package com.vn.hcmute.team.cortana.mymoney.data.service.category;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;

public interface CategoryService {
	
	List<Category> getDefaultCategory(String userid)throws RuntimeException;
	
	List<Category> getCategoryByUserId(String userid)throws RuntimeException;
	
	void addCategory(Category category)throws RuntimeException;
	
	void updateCategory(Category category)throws RuntimeException;
	
	void removeCategory(Category category)throws RuntimeException;
	
	boolean isExistsCategory(Category category)throws RuntimeException;
}
