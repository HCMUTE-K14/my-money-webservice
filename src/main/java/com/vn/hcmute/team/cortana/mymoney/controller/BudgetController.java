package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.model.BudgetModel;

@SuppressWarnings("unchecked")
@Component
@Path("budget")
public class BudgetController {
	@Autowired
	BudgetModel mBudgetModel;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String home() {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		
		response.setStatus("success");
		response.setMessage("ok");
		response.setData("budget");
		
		return response.toString();
	}
	
	@GET
	@Path("getBudget")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getInfo(@QueryParam("userid") String userid,@QueryParam("token") String token) {
		Class<List<Budget>> clazz=(Class<List<Budget>>)(Object)List.class;
		JsonResponse<List<Budget>> response=new JsonResponse<List<Budget>>(clazz);
		CallBack<List<Budget>> callBack=new CallBack<List<Budget>>() {
			
			@Override
			public void onSuccess(List<Budget> result) {
				// TODO Auto-generated method stub
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mBudgetModel.getBudget(userid, token, callBack);
		
		return response.toString();
	}
	@POST
	@Path("createBudget")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createBudget(Budget budget,@QueryParam("userid") String userid,@QueryParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println("Call back in Controller");
				response.setStatus("success");
				response.setMessage("Create Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				System.out.println(e.getMessage());
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mBudgetModel.createBudget(budget, userid, token, callBack);
		return response.toString();
	}
	@POST
	@Path("updateBudget")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBudget(Budget budget,@QueryParam("userid") String userid,@QueryParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println("Call back in Controller");
				response.setStatus("success");
				response.setMessage("Update Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				System.out.println(e.getMessage());
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mBudgetModel.updateBudget(budget, userid, token, callBack);
		return response.toString();
	}
	@GET
	@Path("deleteBudget")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBudget(@QueryParam("userid") String userid,@QueryParam("token") String token,
			@QueryParam("budgetId") String budgetId) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println("Call back in Controller");
				response.setStatus("success");
				response.setMessage("delete Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				System.out.println(e.getMessage());
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mBudgetModel.deleteBudget(userid, token, budgetId, callBack);
		return response.toString();
	}
}
