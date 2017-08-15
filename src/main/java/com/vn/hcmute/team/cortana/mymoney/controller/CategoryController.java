package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.Constraint;
import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.model.CategoryModel;

@Component
@Path("category")
public class CategoryController {
	public static final Log LOG = LogFactory.getLog(CategoryController.class);

	@Autowired
	CategoryModel mCategoryModel;

	public CategoryController() {

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Category home() {
		return new Category();
	}

	@SuppressWarnings("unchecked")
	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@DefaultValue("default") @QueryParam("type") String type, @QueryParam("uid") String userId,
			@QueryParam("token") String token) {

		Class<List<Category>> clazz = (Class<List<Category>>) (Object) List.class;
		JsonResponse<List<Category>> response = new JsonResponse<List<Category>>(clazz);
		
		CallBack<List<Category>> callback = new CallBack<List<Category>>() {

			@Override
			public void onSuccess(List<Category> result) {
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);

			}

			@Override
			public void onFailure(Throwable e) {
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
				LOG.info("get fail");

			}
		};

		if (type.equals(Constraint.CATEGORY_TYPE_CUSTOM)) {
			mCategoryModel.getCategoryByUserId(userId, token, callback);
		} else {
			mCategoryModel.getDefaultCategory(userId,token,callback);
		}

		return response.toString();
	}
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add(@QueryParam("uid") String userId, @QueryParam("token") String token, Category category) {
		JsonResponse<String> response = new JsonResponse<String>(String.class);
		CallBack<String> callback = new CallBack<String>() {

			@Override
			public void onSuccess(String result) {
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}

			@Override
			public void onFailure(Throwable e) {
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mCategoryModel.addCategory(userId, token, category, callback);
		return response.toString();
	}

}
