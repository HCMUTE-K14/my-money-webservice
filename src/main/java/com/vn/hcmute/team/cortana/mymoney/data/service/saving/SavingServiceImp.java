package com.vn.hcmute.team.cortana.mymoney.data.service.saving;

import java.util.List;

import org.glassfish.hk2.runlevel.RunLevelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
@Component
public class SavingServiceImp implements SavingService{
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	
	@Override
	public List<Saving> getSaving(String userid) {
		try {
			Query searchQuery = new Query(Criteria.where("userid").is(userid));
			List<Saving> list=mongoTemplate.find(searchQuery, Saving.class,DbConstraint.TABLE_SAVING);
			return list;
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
			
	}

	@Override
	public void createSaving(Saving saving) {
		try {
			mongoTemplate.save(saving, DbConstraint.TABLE_SAVING);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void updateSaving(Saving saving) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("savingid").is(saving.getSavingid()).and("userid").is(saving.getUserid()));


			Saving saving1 = mongoTemplate.findOne(query, Saving.class,DbConstraint.TABLE_SAVING);
			if(saving1==null) {
				throw new RuntimeException("Null Saving!");
			}
			
		/*	saving1.setName(saving.getName());
			saving1.setGoalMoney(saving.getGoalMoney());
			saving1.setStartMoney(saving.getStartMoney());
			saving1.setDate(saving.getDate());
			saving1.setIdWallet(saving.getIdWallet());
			saving1.setIdCurrencies(saving.getIdCurrencies());
			saving1.setStatus(saving.getStatus());
		
		
			mongoTemplate.save(saving1,DbConstraint.TABLE_SAVING);*/
			
			Update update=new Update();
			update.set("name", saving.getName());
			update.set("goalMoney", saving.getGoalMoney());
			update.set("startMoney", saving.getStartMoney());
			update.set("currentMoney", saving.getCurrentMoney());
			update.set("date", saving.getDate());
			update.set("idWallet", saving.getIdWallet());
			update.set("idCurrencies", saving.getIdCurrencies());
			update.set("status", saving.getStatus());
			
			
			mongoTemplate.updateFirst(query, update, Saving.class,DbConstraint.TABLE_SAVING);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void deleteSaving(String idSaving) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("savingid").is(idSaving));
			Saving saving=mongoTemplate.findOne(query, Saving.class,DbConstraint.TABLE_SAVING);
			if(saving==null)
				throw new RuntimeException("Null Saving!");
			mongoTemplate.remove(query,Saving.class,DbConstraint.TABLE_SAVING);
			
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void takeIn(String idWallet, String idSaving,String money) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("walletid").is(idWallet));
			Wallet walletFrom = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
			
			Query query2=new Query();
			query2.addCriteria(Criteria.where("savingid").is(idSaving));
			Saving saving=mongoTemplate.findOne(query2, Saving.class,DbConstraint.TABLE_SAVING);
			
			if(walletFrom==null)
				throw new RuntimeException("Null wallet!");
			if(saving==null)
				throw new RuntimeException("Null saving!");
			
			double moneyWallet=Double.parseDouble(walletFrom.getMoney());
			double moneySaving=Double.parseDouble(saving.getCurrentMoney());
			double moneyGoalMoney=Double.parseDouble(saving.getGoalMoney());
			double moneyTakeIn=Double.parseDouble(money);
			
			if(moneyTakeIn>moneyWallet)
				throw new RuntimeException("Over money take in!");
			
			if(moneyTakeIn+moneySaving>moneyGoalMoney) {
				if(moneySaving==moneyGoalMoney)
					throw new RuntimeException("Full saving!");
				moneyWallet=moneyWallet-moneyTakeIn+(moneyTakeIn-(moneyGoalMoney-moneySaving));
				moneySaving=moneyGoalMoney;
				
			}else {
				moneyWallet=moneyWallet-moneyTakeIn;
				moneySaving=moneySaving+moneyTakeIn;
			}
			
			
			/*walletFrom.setMoney(String.valueOf(moneyWallet));
			saving.setCurrentMoney(String.valueOf(moneySaving));*/
			
			Update update=new Update();
			update.set("money", String.valueOf(moneyWallet));
			mongoTemplate.updateFirst(query, update, Wallet.class,DbConstraint.TABLE_WALLET);
			
			Update update2=new Update();
			update2.set("currentMoney",String.valueOf( moneySaving));
			mongoTemplate.updateFirst(query2, update2, Saving.class,DbConstraint.TABLE_SAVING);
			/*mongoTemplate.save(walletFrom,DbConstraint.TABLE_WALLET);
			mongoTemplate.save(saving,DbConstraint.TABLE_SAVING);*/
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

	@Override
	public void takeOut(String idWallet, String idSaving,String money) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("walletid").is(idWallet));
			Wallet walletFrom = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
			
			Query query2=new Query();
			query2.addCriteria(Criteria.where("savingid").is(idSaving));
			Saving saving=mongoTemplate.findOne(query2, Saving.class,DbConstraint.TABLE_SAVING);
			
			if(walletFrom==null)
				throw new RuntimeException("Null wallet!");
			if(saving==null)
				throw new RuntimeException("Null saving!");
			
			double moneyWallet=Double.parseDouble(walletFrom.getMoney());
			double moneySaving=Double.parseDouble(saving.getCurrentMoney());
			double moneyTakeOut=Double.parseDouble(money);
			
			if(moneyTakeOut>moneySaving)
				throw new RuntimeException("Over money take out!");
			
			moneyWallet=moneyWallet+moneyTakeOut;
			moneySaving=moneySaving-moneyTakeOut;
			
			Update update=new Update();
			update.set("money",String.valueOf( moneyWallet));
			mongoTemplate.updateFirst(query, update, Wallet.class,DbConstraint.TABLE_WALLET);
			
			Update update2=new Update();
			update2.set("currentMoney", String.valueOf(moneySaving));
			mongoTemplate.updateFirst(query2, update2, Saving.class,DbConstraint.TABLE_SAVING);
			
			/*mongoTemplate.save(walletFrom,DbConstraint.TABLE_WALLET);
			mongoTemplate.save(saving,DbConstraint.TABLE_SAVING);*/
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

}
