package com.vn.hcmute.team.cortana.mymoney.data.service.currencies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;

@Component
public class CurrenciesServiceImp implements CurrenciesService{
	@Autowired
	MongoTemplate mongoTemplate;
	
	public CurrenciesServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Currencies> getCurrencies() {
		List<Currencies> listCurrencies = mongoTemplate.findAll(Currencies.class,DbConstraint.TABLE_CURRENCIES);
		return listCurrencies;
	}

}
