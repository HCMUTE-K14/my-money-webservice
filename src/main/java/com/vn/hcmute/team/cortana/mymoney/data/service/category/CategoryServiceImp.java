package com.vn.hcmute.team.cortana.mymoney.data.service.category;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.CategoryException;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
import com.vn.hcmute.team.cortana.mymoney.utils.ResourceUtil;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;

@Component
public class CategoryServiceImp implements CategoryService {
	public static final Log LOG = LogFactory.getLog(CategoryServiceImp.class);

	@Autowired
	private MongoTemplate mMongoTemplate;

	public CategoryServiceImp() {

	}

	@Override
	public void addCategory(Category category, String parentId) {
		try {
			LOG.info("Category exists ?..." + isExistsCategory(category));
			if (isExistsCategory(category)) {
				throw new CategoryException("Category exists");
			}
			if (TextUtil.isEmpty(parentId)) {
				mMongoTemplate.save(category, DbConstraint.TABLE_CATEGORY);
			} else {
				System.out.println("CATEGORY_ADD:" + parentId);
				Query query = new Query();

				query.addCriteria(where("cate_id").is(parentId).and("userid").is(category.getUserid()));

				Category cate = mMongoTemplate.findOne(query, Category.class, DbConstraint.TABLE_CATEGORY);

				Update update = new Update();
				List<Category> subscate = cate.getSubcategories();
				for(Category _cate:subscate){
					if(_cate.getName().trim().equals(category.getName().trim())){
						throw new CategoryException("This category already exsited");
					}
				}
				subscate.add(category);
				update.set("subcategories", subscate);

				mMongoTemplate.updateFirst(query, update, Category.class, DbConstraint.TABLE_CATEGORY);
			}
			LOG.info("Insert category...successful");
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void updateCategory(Category category, String oldParentId, String newParentId) {
		try {
			if (TextUtil.isEmpty(oldParentId) && TextUtil.isEmpty(newParentId)) {

				Query query = new Query();

				query.addCriteria(where("cate_id").is(category.getCate_id()).and("userid").is(category.getUserid()));

				Category cate = mMongoTemplate.findOne(query, Category.class, DbConstraint.TABLE_CATEGORY);

				if (cate == null) {
					throw new CategoryException("This category already exsited");
				}

				long count = mMongoTemplate.count(query(where("name").is(category.getName())), Category.class,
						DbConstraint.TABLE_CATEGORY);
				Update update = new Update();
				if (count > 0 && !cate.getIcon().equals(category.getIcon())) {
					update.set("icon", category.getIcon());
					update.set("subcategories", category.getSubcategories());
					update.set("name", category.getName());
				} else if (count > 0 && cate.getIcon().equals(category.getIcon())) {
					throw new CategoryException("This category already exsited");
				} else if (count <= 0) {
					update.set("icon", category.getIcon());
					update.set("subcategories", category.getSubcategories());
					update.set("name", category.getName());
				}

				mMongoTemplate.updateFirst(query, update, Category.class, DbConstraint.TABLE_CATEGORY);
			} else if (!TextUtil.isEmpty(oldParentId)) {
				LOG.info("UPDATE 2");
				LOG.info("NewParent"+newParentId);
				LOG.info("OldParent"+oldParentId);

				Query query = new Query();
				Update update = new Update();

				query.addCriteria(where("cate_id").is(oldParentId).and("userid").is(category.getUserid()));

				Category cate = mMongoTemplate.findOne(query, Category.class, DbConstraint.TABLE_CATEGORY);

				if (cate == null) {
					throw new CategoryException("Category not exists");
				}

				if (TextUtil.isEmpty(newParentId)) {
					ArrayList<Category> tempList = (ArrayList<Category>) cate.getSubcategories();
					for (Category _cate : tempList) {
						if (_cate.getName().trim().equals(category.getName().trim())
								&& _cate.getIcon().equals(category.getIcon())) {
							throw new CategoryException("This category already exsited");
						}else if(_cate.getName().trim().equals(category.getName().trim()) 
								&& !_cate.getIcon().equals(category.getIcon())){}
						else {}
					}
					tempList.remove(category);
					tempList.add(category);
					update.set("subcategories", tempList);
					mMongoTemplate.updateFirst(query, update, Category.class, DbConstraint.TABLE_CATEGORY);
					return;
				}

				// Delete old category
				cate.getSubcategories().remove(category);
				update.set("subcategories", cate.getSubcategories());
				mMongoTemplate.updateFirst(query, update, Category.class, DbConstraint.TABLE_CATEGORY);
				// Move category
				Query query1 = new Query();
				query1.addCriteria(where("cate_id").is(newParentId).and("userid").is(category.getUserid()));
				Category cate_parent = mMongoTemplate.findOne(query1, Category.class, DbConstraint.TABLE_CATEGORY);
				if (cate_parent == null) {
					throw new CategoryException("Category not exists");
				}
				cate_parent.getSubcategories().add(category);
				Update update1 = new Update();
				update1.set("subcategories", cate_parent.getSubcategories());
				mMongoTemplate.updateFirst(query1, update1, Category.class, DbConstraint.TABLE_CATEGORY);
			}

		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void removeCategory(Category category, String parentId) {
		try {
			if (!isExistsCategory(category)) {
				throw new CategoryException("Cannot found category");
			}
			if (TextUtil.isEmpty(parentId)) {
				Query query = new Query();
				query.addCriteria(where("cate_id").is(category.getCate_id()).and("userid").is(category.getUserid()));
				mMongoTemplate.remove(query, Category.class, DbConstraint.TABLE_CATEGORY);
				LOG.info("Remove category...successful");
			} else {
				Query query = new Query();
				query.addCriteria(where("cate_id").is(category.getCate_id()).and("userid").is(category.getUserid()));
				Category cate = mMongoTemplate.findOne(query, Category.class, DbConstraint.TABLE_CATEGORY);

				if (cate == null) {
					throw new CategoryException("Category not exists");
				}
				Update update = new Update();
				cate.getSubcategories().remove(category);
				update.set("subcategories", cate.getSubcategories());

				mMongoTemplate.updateFirst(query, update, Category.class, DbConstraint.TABLE_CATEGORY);
			}

		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public boolean isExistsCategory(Category category) {
		try {
			Category cate = mMongoTemplate.findOne(
					query(where("name").is(category.getName()).and("userid").is(category.getUserid())), Category.class,
					DbConstraint.TABLE_CATEGORY);

			return cate != null ? true : false;
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public List<Category> getCategoryByUserId(String userid) {
		try {
			List<Category> categories = mMongoTemplate.find(query(where("userid").is(userid)), Category.class,
					DbConstraint.TABLE_CATEGORY);
			if (categories == null) {
				LOG.info("Cannot get category custom");
				throw new CategoryException("Cannot get custom category");
			}

			LOG.info("Get category...successful");
			return categories;
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void initDefaultCategory(String userid) {
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(new InputStreamReader(ResourceUtil.getDefaultCategory(), "UTF-8"));
			Category[] listcategory = new Gson().fromJson(jsonReader, Category[].class);
			if (listcategory == null || listcategory.length == 0) {
				throw new CategoryException("Cannot get default category");
			}
			for (Category category : listcategory) {
				category.setUserid(userid);
				for (Category subcate : category.getSubcategories()) {
					subcate.setUserid(userid);
				}
			}
			List<Category> list = Arrays.asList(listcategory);
			mMongoTemplate.insert(list, DbConstraint.TABLE_CATEGORY);

		} catch (UnsupportedEncodingException e) {
			throw new CategoryException("Cannot get default category");
		}
	}

	@Override
	public synchronized void syncCategory(List<Category> categories) throws RuntimeException {
		try {
			if (categories == null || categories.isEmpty()) {
				throw new RuntimeException("List of category is empty");
			}
			List<Category> listCategoryRemote = getCategoryByUserId(categories.get(0).getUserid());

			for (int i = 0; i < listCategoryRemote.size(); i++) {
				if (!categories.contains(listCategoryRemote.get(i))) {
					removeCategory(listCategoryRemote.get(i), null);
				}
			}

			Query query = new Query();
			for (int i = 0; i < categories.size(); i++) {
				query.addCriteria(Criteria.where("cate_id").is(categories.get(i).getCate_id()).and("userid")
						.is(categories.get(i).getUserid()));

				Category _category = mMongoTemplate.findOne(query, Category.class, DbConstraint.TABLE_CATEGORY);
				if (_category == null) {
					mMongoTemplate.save(categories.get(i), DbConstraint.TABLE_CATEGORY);
					continue;
				}

				Update update = new Update();
				update.set("name", _category.getName());
				update.set("icon", _category.getIcon());
				update.set("subcategories", _category.getSubcategories());

				mMongoTemplate.updateFirst(query, update, Category.class, DbConstraint.TABLE_CATEGORY);
			}

		} catch (MongoException e) {
			throw new DatabaseException("Something wrong ! Please try later");
		}

	}

	@Override
	public List<Category> getCategoryByTransactionType(String userid, String transactionType) throws RuntimeException {

		try {
			List<Category> categories = mMongoTemplate.find(
					query(where("userid").is(userid).and("trans_type").is(transactionType)), Category.class,
					DbConstraint.TABLE_CATEGORY);
			if (categories == null) {
				LOG.info("Cannot get category custom");
				throw new CategoryException("Cannot get custom category");
			}

			LOG.info("Get category...successful");
			return categories;
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public List<Category> getCategoryByType(String userid, String type, String transType) throws RuntimeException {
		try {
			List<Category> categories = mMongoTemplate.find(
					query(where("userid").is(userid).and("type").is(type).and("trans_type").is(transType)),
					Category.class, DbConstraint.TABLE_CATEGORY);
			if (categories == null) {
				LOG.info("Cannot get category custom");
				throw new CategoryException("Cannot get custom category");
			}

			LOG.info("Get category...successful");
			return categories;
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}
}
