package com.vn.hcmute.team.cortana.mymoney.data.service.wallet;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;

@Component
public class WalletServiceImp implements WalletService{
	public  static final Log LOG=LogFactory.getLog(WalletServiceImp.class);
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public WalletServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Wallet> getInfoWallet(String userid) {
		try {
			Query searchQuery = new Query(Criteria.where("userid").is(userid));
			List<Wallet> listWallet=mongoTemplate.find(searchQuery, Wallet.class,DbConstraint.TABLE_WALLET);
			return listWallet;
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	
	}

	@Override
	public void createWallet(Wallet wallet) {
		try {
			
			LOG.info("Insert document...");
			mongoTemplate.save(wallet, DbConstraint.TABLE_WALLET);
			LOG.info("Insert successful...");
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void deleteWallet( String userid, String idwallet) {
		try {
			LOG.info("Delete document...");
			Query query = new Query();
			query.addCriteria(Criteria.where("walletid").is(idwallet).and("userid").is(userid));
			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
			if(wallet2==null)
				throw new RuntimeException("Null Wallet");
			mongoTemplate.remove(query, Wallet.class, DbConstraint.TABLE_WALLET);
			LOG.info("Delete successful...");
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

	@Override
	public void updateWallet(Wallet wallet) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("walletid").is(wallet.getWalletid()));


			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
			
			if(wallet2==null) throw new RuntimeException("Null wallet!");
			
			Update  update=new Update();
			update.set("walletName", wallet.getWalletName());
			update.set("money", wallet.getMoney());
			update.set("currencyUnit", wallet.getCurrencyUnit());
			update.set("walletImage", wallet.getWalletImage());
			mongoTemplate.updateFirst(query, update, Wallet.class,DbConstraint.TABLE_WALLET);
			
			
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

	@Override
	public void moveMoneyWallet(String userid, String idWalletFrom, String idWallet, String money) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("walletid").is(idWalletFrom).and("userid").is(userid));
			Wallet walletFrom = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
			
			Query query1 = new Query();
			query1.addCriteria(Criteria.where("walletid").is(idWallet).and("userid").is(userid));
			Wallet walletTo = mongoTemplate.findOne(query1, Wallet.class,DbConstraint.TABLE_WALLET);
			
			try {
				double moneyFrom=Double.parseDouble(walletFrom.getMoney());
				double moneyTo=Double.parseDouble(walletTo.getMoney());
				double moneyM=Double.parseDouble(money);
				moneyFrom=moneyFrom-moneyM;
				moneyTo=moneyTo+moneyM;
			
				
				Update update=new Update();
				update.set("money", String.valueOf(moneyFrom));
				mongoTemplate.updateFirst(query, update, Wallet.class,DbConstraint.TABLE_WALLET);
				
				Update update2=new Update();
				update2.set("money", String.valueOf(moneyTo));
				
				mongoTemplate.updateFirst(query1, update2,Wallet.class,DbConstraint.TABLE_WALLET);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void syncWallet(List<Wallet> list) {
		if(list==null) {
			throw new RuntimeException("null list");
		}
				
		List<Wallet> listWallet=getInfoWallet(list.get(0).getUserid());
		
		for(int i=0;i<listWallet.size();i++) {
			if(!list.contains(listWallet.get(i))) {
				deleteWallet(listWallet.get(i).getUserid(), listWallet.get(i).getWalletid());
			}
		}
		
		for (Wallet wallet : list) {
			sync(wallet);
		}
		
	}
	public void sync(Wallet wallet) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("walletid").is(wallet.getWalletid()));


			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
			
			if(wallet2==null) {
				createWallet(wallet);
				return;
				
			}
			
			Update  update=new Update();
			update.set("walletName", wallet.getWalletName());
			update.set("money", wallet.getMoney());
			update.set("currencyUnit", wallet.getCurrencyUnit());
			update.set("walletImage", wallet.getWalletImage());
			mongoTemplate.updateFirst(query, update, Wallet.class,DbConstraint.TABLE_WALLET);
			
			
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}
	


}
