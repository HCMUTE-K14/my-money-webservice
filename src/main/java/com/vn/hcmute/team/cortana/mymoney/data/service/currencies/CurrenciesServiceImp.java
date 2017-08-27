package com.vn.hcmute.team.cortana.mymoney.data.service.currencies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;

@Component
public class CurrenciesServiceImp implements CurrenciesService{
	@Autowired
	MongoTemplate mongoTemplate;
	
	public CurrenciesServiceImp() {
		
	}
	
	@Override
	public List<Currencies> getCurrencies() {
		try {
			List<Currencies> listCurrencies = mongoTemplate.findAll(Currencies.class,DbConstraint.TABLE_CURRENCIES);
			return listCurrencies;
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

}
