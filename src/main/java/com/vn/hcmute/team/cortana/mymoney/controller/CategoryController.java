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

	@SuppressWarnings("unchecked")
	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@DefaultValue("all") @QueryParam("type") String type, @QueryParam("uid") String userId,
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
		switch (type.trim().toLowerCase()) {
		case "all":
			mCategoryModel.getCategoryByUserId(userId, token, callback);
			break;
		case "income":
		case "expense":
		case "debt_loan":
			mCategoryModel.getCategoryByTransactionType(userId, token, type, callback);
			break;
		}

		return response.toString();
	}

	@SuppressWarnings("unchecked")
	@Path("getByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getByType(@QueryParam("type") String type, @QueryParam("uid") String userId,
			@QueryParam("token") String token,@QueryParam("trans_type")String transType) {

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
		switch (transType.trim().toLowerCase()) {
			case "income":
			case "expense":
			case "debt_loan":
				if(type.equals("income") || type.equals("expense")){
					mCategoryModel.getCategoryByType(userId, token, type,transType, callback);
				}
				break;
			default:
				break;
		}

		return response.toString();
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add(@QueryParam("uid") String userId, 
			@QueryParam("token") String token,
			@QueryParam("parent_id")String parentId, Category category) {
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
		mCategoryModel.addCategory(userId, token, category,parentId, callback);
		return response.toString();
	}

	@POST
	@Path("remove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String remove(@QueryParam("uid") String userId, 
			@QueryParam("token") String token,
			@QueryParam("parent_id")String parentId, Category category) {
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
		mCategoryModel.removeCategory(userId, token, category,parentId, callback);
		return response.toString();
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@QueryParam("uid") String userId,
			@QueryParam("token") String token,
			@QueryParam("new_parent_id")String newParentId,
			@QueryParam("old_parent_id")String oldParentId, Category category) {
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
		mCategoryModel.updateCategory(userId, token, category,oldParentId,newParentId, callback);
		return response.toString();
	}

}
