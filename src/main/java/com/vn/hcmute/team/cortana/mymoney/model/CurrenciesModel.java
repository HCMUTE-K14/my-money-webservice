package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
@Component
public class CurrenciesModel {
	DataRepository dataRepository;
	
	@Autowired
	public CurrenciesModel(DataRepository dataRepository) {
		// TODO Auto-generated constructor stub
		this.dataRepository=dataRepository;
	}
	public void getCurrencies(String userid,String token,CallBack<List<Currencies>> resultCurrencies){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				resultCurrencies.onFailure(new Throwable("Fail get Currencies!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				resultCurrencies.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Currencies> list=dataRepository.getCurrencies();
			resultCurrencies.onSuccess(list);
		}catch(Exception e){
			resultCurrencies.onFailure(new Throwable("Fail get Currencies!"));
		}
	}
}
