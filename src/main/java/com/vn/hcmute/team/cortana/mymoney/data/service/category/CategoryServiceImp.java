package com.vn.hcmute.team.cortana.mymoney.data.service.category;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.CategoryException;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;

@Component
public class CategoryServiceImp implements CategoryService {
	public static final Log LOG = LogFactory.getLog(CategoryServiceImp.class);

	@Autowired
	private MongoTemplate mMongoTemplate;

	public CategoryServiceImp() {

	}

	@Override
	public void addCategory(Category category) {
		try {
			LOG.info("Category exists ?..."+isExistsCategory(category));
			if (isExistsCategory(category)) {
				throw new CategoryException("Category exists");
			}
			mMongoTemplate.save(category, DbConstraint.TABLE_CATEGORY);
			LOG.info("Insert category...successful");
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void updateCategory(Category category) {
		try {
			LOG.info("update category");
			
			Query query = new Query();
	
			query.addCriteria(where("categoryId").is(category.getCategoryId()).and("userId").is(category.getUserId()));

			Category cate = mMongoTemplate.findOne(query, Category.class,DbConstraint.TABLE_CATEGORY);
			
			if(cate==null){
				throw new CategoryException("Category not exists");
			}
			
		
			Update update=new Update();
			
			update.set("categoryName", category.getCategoryName());
			update.set("categoryParent", category.getCategoryParent());

			mMongoTemplate.updateFirst(query,update,Category.class, DbConstraint.TABLE_CATEGORY);
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void removeCategory(Category category) {
		try{
			if (!isExistsCategory(category)) {
				throw new CategoryException("Cannot found category");
			}
			Query query = new Query();
			query.addCriteria(where("categoryId").is(category.getCategoryId()).and("userId").is(category.getUserId()));
			mMongoTemplate.remove(query, Category.class, DbConstraint.TABLE_CATEGORY);
			LOG.info("Remove category...successful");
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public boolean isExistsCategory(Category category) {
		try {
			Category cate = mMongoTemplate.findOne(query(
					where("categoryName").is(category.getCategoryName()).and("userId").is(category.getUserId())),
					Category.class, DbConstraint.TABLE_CATEGORY);

			return cate != null ? true : false;
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public List<Category> getDefaultCategory(String userid) {
		try{
			List<Category> result=mMongoTemplate.find(query(where("categoryType").is(Category.TYPE_DEFAULT).and("userId").is(userid)), Category.class,DbConstraint.TABLE_CATEGORY);
			if(result==null || result.isEmpty()){
				LOG.info("Cannot get category default");
				throw new CategoryException("Cannot get category default");
			}
			LOG.info("Get default category...successful");
			return result;
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public List<Category> getCategoryByUserId(String userid) {
		try{
			List<Category> defaultCategory=getDefaultCategory(userid);
			List<Category> customCategory=mMongoTemplate.find(query(where("categoryType").is(Category.TYPE_CUSTOM).and("userId").is(userid)), Category.class,DbConstraint.TABLE_CATEGORY);
			if(customCategory==null){
				LOG.info("Cannot get category custom");
				throw new CategoryException("Cannot get custom category");
			}
			
			defaultCategory.addAll(customCategory);
			LOG.info("Get category...successful");
			return defaultCategory;
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}
}
