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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.service.wallet.WalletServiceImp;
import com.vn.hcmute.team.cortana.mymoney.model.WalletModel;

@Component
@Path("wallet")
public class WalletController {
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
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(Wallet wallet) {
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
		walletModel.createWallet(wallet, callBack);
		return response.toString();
	}
	@GET
	@Path("getInfoWallet/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getInfo(@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<Wallet> response=new JsonResponse<Wallet>(Wallet.class);
		CallBack<List<Wallet>> callBack=new CallBack<List<Wallet>>() {
			
			@Override
			public void onSuccess(List<Wallet> result) {
				// TODO Auto-generated method stub
				response.setStatus("success");
				response.setMessage("ok");
				response.setListData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		walletModel.getInfoWallet(userid,token, callBack);
		
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
		walletModel.deleteWallet(userid, token, idwallet, callBack);

		return response.toString();
	}
	@POST
	@Path("updateWallet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(Wallet wallet) {
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
		walletModel.updateWallet(wallet, callBack);
		return response.toString();
	}
	@GET
	@Path("moveMoneyWallet/{userid}/{token}/{wallet1}/{wallet2}/{money}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String move(@PathParam("userid") String userid,@PathParam("token") String token,
			@PathParam("wallet1") String wallet1,@PathParam("wallet2") String wallet2,
			@PathParam("money") String money) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack =new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
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
		walletModel.moveMoneyWallet(userid, token, wallet1,wallet2,money,callBack);

		return response.toString();
	}
}
