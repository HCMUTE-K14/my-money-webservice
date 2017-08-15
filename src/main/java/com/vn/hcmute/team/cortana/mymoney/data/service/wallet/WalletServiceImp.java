package com.vn.hcmute.team.cortana.mymoney.data.service.wallet;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;

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
		Query searchQuery = new Query(Criteria.where("userid").is(userid));
		List<Wallet> listWallet=mongoTemplate.find(searchQuery, Wallet.class,DbConstraint.TABLE_WALLET);
		return listWallet;
	}

	@Override
	public void createWallet(Wallet wallet) {
		//May try catch may cho nay het  con khong thi throws trong interface day khi nao loi thi quang excepra 
		LOG.info("Insert document...");
		mongoTemplate.save(wallet, DbConstraint.TABLE_WALLET);
		LOG.info("Insert successful...");
		
	}

	@Override
	public void deleteWallet( String userid, String idwallet) {
		LOG.info("Delete document...");
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(idwallet).and("userid").is(userid));
		Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
		mongoTemplate.remove(wallet2,DbConstraint.TABLE_WALLET);	
		LOG.info("Delete successful...");
	}

	@Override
	public void updateWallet(Wallet wallet) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(wallet.getId()));


		Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);

		wallet2.setWalletName(wallet.getWalletName());
		wallet2.setMoney(wallet.getMoney());
	
		mongoTemplate.save(wallet2,DbConstraint.TABLE_WALLET);
		
	}

	@Override
	public void moveMoneyWallet(String userid, String idWalletFrom, String idWallet, String money) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(idWalletFrom).and("userid").is(userid));
		Wallet walletFrom = mongoTemplate.findOne(query, Wallet.class,DbConstraint.TABLE_WALLET);
		
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("id").is(idWallet).and("userid").is(userid));
		Wallet walletTo = mongoTemplate.findOne(query1, Wallet.class,DbConstraint.TABLE_WALLET);
		try {
			double moneyFrom=Double.parseDouble(walletFrom.getMoney());
			double moneyTo=Double.parseDouble(walletTo.getMoney());
			double moneyM=Double.parseDouble(money);
			moneyFrom=moneyFrom-moneyM;
			moneyTo=moneyTo+moneyM;
			walletFrom.setMoney(String.valueOf(moneyFrom));
			walletTo.setMoney(String.valueOf(moneyTo));
			
			mongoTemplate.save(walletFrom,DbConstraint.TABLE_WALLET);
			mongoTemplate.save(walletTo,DbConstraint.TABLE_WALLET);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
	}
	


}
