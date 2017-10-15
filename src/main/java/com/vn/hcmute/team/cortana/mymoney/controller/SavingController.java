package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.model.SavingModel;

@SuppressWarnings("unchecked")
@Component
@Path("saving")
public class SavingController {
	private static final String UTF8="; charset=UTF-8";
	@Autowired
	SavingModel savingModel;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String home() {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		
		response.setStatus("success");
		response.setMessage("ok");
		response.setData("saving");
		
		return response.toString();
	}
	@POST
	@Path("create/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(Saving saving,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("Create Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		savingModel.createSaving(userid, token, saving, callBack);
		return response.toString();
	}
	@GET
	@Path("getSaving/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON+UTF8)
	@Produces(MediaType.APPLICATION_JSON+UTF8)
	public String getInfo(@PathParam("userid") String userid,@PathParam("token") String token) {
		Class<List<Saving>> clazz = (Class<List<Saving>>) (Object) List.class;
		JsonResponse<List<Saving>> response=new JsonResponse<List<Saving>>(clazz);
		CallBack<List<Saving>> callBack=new CallBack<List<Saving>>() {
			
			@Override
			public void onSuccess(List<Saving> result) {
				
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
		savingModel.getSaving(userid, token, callBack);
		
		return response.toString();
	}
	@POST
	@Path("update/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(Saving saving,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("Update Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		savingModel.updateSaving(userid, token, saving, callBack);
		return response.toString();
	}
	@GET
	@Path("delete/{userid}/{token}/{idSaving}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("userid") String userid,@PathParam("token") String token,
			@PathParam("idSaving") String idSaving) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("delete Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		savingModel.deleteSaving(userid, token, idSaving, callBack);
		return response.toString();
	}
	@GET
	@Path("takeIn/{userid}/{token}/{idWallet}/{idSaving}/{moneyUpdateWallet}/{moneyUpdateSaving}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String takeIn(@PathParam("userid") String userid,@PathParam("token") String token,
			@PathParam("idSaving") String idSaving,@PathParam("idWallet") String idWallet,
			@PathParam("moneyUpdateWallet") String moneyUpdateWallet,
			@PathParam("moneyUpdateSaving") String moneyUpdateSaving) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("take in Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		savingModel.takeIn(userid, token, idWallet, idSaving, moneyUpdateWallet,moneyUpdateSaving, callBack);
		return response.toString();
	}
	@GET
	@Path("takeOut/{userid}/{token}/{idWallet}/{idSaving}/{moneyUpdateWallet}/{moneyUpdateSaving}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String takeOut(@PathParam("userid") String userid,@PathParam("token") String token,
			@PathParam("idSaving") String idSaving,@PathParam("idWallet") String idWallet,
			@PathParam("moneyUpdateWallet") String moneyUpdateWallet,
			@PathParam("moneyUpdateSaving") String moneyUpdateSaving){
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("take out Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		savingModel.takeOut(userid, token, idWallet, idSaving, moneyUpdateWallet,moneyUpdateSaving, callBack);
		return response.toString();
	}
	@POST
	@Path("sync/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String syncSaving(List<Saving> list,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("Sync Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		savingModel.syncSaving(list, userid, token, callBack);
		return response.toString();
	}
}
