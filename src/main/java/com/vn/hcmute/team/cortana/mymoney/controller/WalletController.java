package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.model.WalletModel;

@SuppressWarnings("unchecked")
@Component
@Path("wallet")
public class WalletController {
	private static final String UTF8="; charset=UTF-8";
	@Autowired
	WalletModel walletModel;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String home() {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		
		response.setStatus("success");
		response.setMessage("ok");
		response.setData("wallet");
		
		return response.toString();
	}
	
	@POST
	@Path("create/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(Wallet wallet,@PathParam("userid") String userid,@PathParam("token") String token) {
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
		walletModel.createWallet(wallet,userid,token, callBack);
		return response.toString();
	}
	@GET
	@Path("getInfoWallet/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON+UTF8)
	@Produces(MediaType.APPLICATION_JSON+UTF8)
	public String getInfo(@PathParam("userid") String userid,@PathParam("token") String token) {
		Class<List<Wallet>> clazz = (Class<List<Wallet>>) (Object) List.class;

		JsonResponse<List<Wallet>> response=new JsonResponse<List<Wallet>>(clazz);
		CallBack<List<Wallet>> callBack=new CallBack<List<Wallet>>() {
			
			@Override
			public void onSuccess(List<Wallet> result) {
				
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
		walletModel.getInfoWallet(userid,token, callBack);
		
		return response.toString();
	}

	@GET
	@Path("getWalletById")
	@Consumes(MediaType.APPLICATION_JSON+UTF8)
	@Produces(MediaType.APPLICATION_JSON+UTF8)
	public String getWalletById(
		@QueryParam("uid") String userid,
		@QueryParam("token") String token, 
		@QueryParam("wallet_id") String wallet_id){

		JsonResponse<Wallet> response=new JsonResponse<Wallet>(Wallet.class);
		CallBack<Wallet> callBack=new CallBack<Wallet>() {
			
			@Override
			public void onSuccess(Wallet result) {
				
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
		walletModel.getWalletById(userid,token,wallet_id, callBack);
		
		return response.toString();
	}

	@GET
	@Path("deleteWallet/{userid}/{token}/{idwallet}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("userid") String userid,@PathParam("token") String token,@PathParam("idwallet") String idwallet) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack =new CallBack<String>() {
			
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
		walletModel.deleteWallet(userid, token, idwallet, callBack);

		return response.toString();
	}
	@POST
	@Path("updateWallet/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(Wallet wallet,@PathParam("userid") String userid,@PathParam("token") String token) {
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
		walletModel.updateWallet(wallet,userid,token, callBack);
		return response.toString();
	}
	@GET
	@Path("moveMoneyWallet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String move(@QueryParam("uid") String userid,@QueryParam("token") String token,
			@QueryParam("wallet_from") String walletFrom,@QueryParam("wallet_to") String walletTo,
			@QueryParam("money_minus") String moneyMinus,
			@QueryParam("money_plus") String moneyPlus,
			@QueryParam("date_created") String date_created) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack =new CallBack<String>() {
			
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
		walletModel.moveMoneyWallet(userid, token, walletFrom, walletTo, moneyMinus, moneyPlus,date_created, callBack);

		return response.toString();
	}
	@POST
	@Path("sync/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(List<Wallet> list,@PathParam("userid") String userid,@PathParam("token") String token) {
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
		walletModel.syncWallet(list, userid, token, callBack);
		return response.toString();
	}
}
