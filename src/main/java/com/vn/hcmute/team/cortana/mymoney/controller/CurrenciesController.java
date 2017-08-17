package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.model.CurrenciesModel;

@Component
@Path("currency")
public class CurrenciesController {
	private static final String UTF8="; charset=UTF-8";
	@Autowired
	CurrenciesModel currenciesModel;
	 
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String home() {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		
		response.setStatus("success");
		response.setMessage("ok");
		response.setData("currencies");
		
		return response.toString();
	}
	@SuppressWarnings("unchecked")
	@GET
	@Consumes(MediaType.APPLICATION_JSON+ UTF8)
	@Produces(MediaType.APPLICATION_JSON+ UTF8)
	public String getInfo() {
		Class<List<Currencies>> clazz = (Class<List<Currencies>>) (Object) List.class;
		
		JsonResponse<List<Currencies>> response=new JsonResponse<List<Currencies>>(clazz);
		
		CallBack<List<Currencies>> callBack=new CallBack<List<Currencies>>() {
			
			@Override
			public void onSuccess(List<Currencies> result) {
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
		currenciesModel.getCurrencies(callBack);
		
		return response.toString();
	}
}
