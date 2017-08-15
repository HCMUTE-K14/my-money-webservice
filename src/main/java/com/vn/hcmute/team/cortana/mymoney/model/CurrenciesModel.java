package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.*;
@Component
public class CurrenciesModel {
	DataRepository dataRepository;
	
	@Autowired
	public CurrenciesModel(DataRepository dataRepository) {
		// TODO Auto-generated constructor stub
		this.dataRepository=dataRepository;
	}
	public void getCurrencies(CallBack<List<Currencies>> resultCurrencies){
		try{
			
			List<Currencies> list=dataRepository.getCurrencies();
			resultCurrencies.onSuccess(list);
		}catch(Exception e){
			resultCurrencies.onFailure(new Throwable("Fail get Currencies!"));
		}
	}
}
