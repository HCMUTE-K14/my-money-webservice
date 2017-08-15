package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;
import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.*;
@Component
public class CurrenciesModel {
	DataRepository dataRepository;
	
	@Autowired
	SecurityUtil securityUtil;
	@Autowired
	public CurrenciesModel(DataRepository dataRepository) {
		// TODO Auto-generated constructor stub
		this.dataRepository=dataRepository;
	}
	public void getCurrencies(String userid,String token,CallBack<List<Currencies>> resultCurrencies){
		
		if(userid.equals("")||token.equals("")) {
			 resultCurrencies.onFailure(new Throwable("Fail get Currencies!"));
		}else {
			String apiKeyDB=securityUtil.getApiKey(userid);
			//gena
			String apiKey=SecurityUtil.generateApiKey(token);
			
			if(apiKeyDB.equals(apiKey)) {
				List<Currencies> list=dataRepository.getCurrencies();
				resultCurrencies.onSuccess(list);
				
			}else {
				resultCurrencies.onFailure(new Throwable("Fail get Currencies!"));
			}
			
		}
		
	}
}
