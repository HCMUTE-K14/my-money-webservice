package com.vn.hcmute.team.cortana.mymoney.data.service.transaction;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
import com.vn.hcmute.team.cortana.mymoney.exception.TransactionException;
import com.vn.hcmute.team.cortana.mymoney.utils.DateUtil;

@Component
public class TransactionServiceImp implements TransactionService {

	@Autowired
	MongoTemplate mMongoTemplate;

	@Override
	public List<Transaction> getAllTransaction(String userid) {
		try {
			List<Transaction> list = mMongoTemplate.find(query(where("user_id").is(userid)), Transaction.class,
					DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public Transaction getTransactionById(String transactionId,String userid) {
		try {
			Transaction trans = mMongoTemplate.findOne(query(where("trans_id").is(transactionId).and("user_id").is(userid)),
					Transaction.class, DbConstraint.TABLE_TRANSACTION);
			if (trans != null) {
				return trans;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public List<Transaction> getTransactionByType(int type, String userid) {
		try {
			List<Transaction> list = mMongoTemplate.find(query(where("type").is(type).and("user_id").is(userid)),
					Transaction.class, DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public List<Transaction> getTransactionByTime(String startDate, String endDate, String userid) {
		try {

			long start = DateUtil.getMilisecondFromDate(startDate);
			long end = DateUtil.getMilisecondFromDate(endDate);

			Query query = new Query();
			query.addCriteria(Criteria.where("date_created").gte(start).lte(end).and("user_id").is(userid));
			List<Transaction> list = mMongoTemplate.find(query, Transaction.class, DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public List<Transaction> getTransactionByCategory(String categoryId, String userid) {
		try {

			List<Transaction> list = mMongoTemplate.find(
					query(where("category.cate_id").is(categoryId).and("user_id").is(userid)), Transaction.class,
					DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public void addTransaction(Transaction transaction) {
		try {
			mMongoTemplate.save(transaction, DbConstraint.TABLE_TRANSACTION);
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}

	}

	@Override
	public void updateTransaction(Transaction transaction) {
		try {
			Update update=new Update();
			
			update.set("amount", transaction.getAmount());
			update.set("person", transaction.getPerson());
			update.set("note", transaction.getNote());
			update.set("image", transaction.getImage());
			update.set("type", transaction.getType());
			update.set("event", transaction.getEvent());
			update.set("category",transaction.getCategory());
			update.set("latitude", transaction.getLatitude());
			update.set("longitude", transaction.getLongitude());
			update.set("wallet", transaction.getWallet());
			update.set("date_created", transaction.getDate_created());
			update.set("date_end", transaction.getDate_end());
			
			mMongoTemplate.updateFirst(query(where("trans_id").is(transaction.getTrans_id()).and("user_id").is(transaction.getUser_id())), 
					update, 
					Transaction.class,
					DbConstraint.TABLE_TRANSACTION);
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public void removeTransaction(String transactionId,String userid) {
		try {
			mMongoTemplate.findAndRemove(query(where("trans_id").is(transactionId).and("user_id").is(userid)), Transaction.class, DbConstraint.TABLE_TRANSACTION);
			
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}

	}

	@Override
	public List<Transaction> getTransactionByType(int type, String userid, String walletId) {
		try {
			List<Transaction> list = mMongoTemplate.find(query(where("type").is(type).and("user_id").is(userid).and("wallet.wallet_id").is(walletId)),
					Transaction.class, DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public List<Transaction> getTransactionByTime(String startDate, String endDate, String userid, String walletId) {
		try {
			long start = DateUtil.getMilisecondFromDate(startDate);
			long end = DateUtil.getMilisecondFromDate(endDate);

			Query query = new Query();
			query.addCriteria(Criteria.where("date_created").gte(start).lte(end).and("user_id").is(userid).and("wallet.wallet_id").is(walletId));
			List<Transaction> list = mMongoTemplate.find(query, Transaction.class, DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public List<Transaction> getTransactionByCategory(String categoryId, String userid, String walletId) {
		try {
			List<Transaction> list = mMongoTemplate.find(
					query(where("category.cate_id").is(categoryId).and("user_id").is(userid).and("wallet.wallet_id").is(walletId)), Transaction.class,
					DbConstraint.TABLE_TRANSACTION);
			if (list != null) {
				return list;
			}
			throw new TransactionException("Cannot get transactions");
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}
	}

	@Override
	public synchronized void syncTransaction(List<Transaction> transactions) {
		Runnable doInBackGround=new Runnable() {
			
			@Override
			public void run() {
				try{
					if(transactions == null || transactions.isEmpty()){
						throw new RuntimeException("List of person is empty");
					}
					String userid=transactions.get(0).getUser_id();
					
					List<Transaction> listTransactionRemote=getAllTransaction(userid);
					
					for(int i=0; i<listTransactionRemote.size();i++){
						if(!transactions.contains(listTransactionRemote.get(i))){
							removeTransaction(
									listTransactionRemote.get(i).getTrans_id(),
									userid);
						}
					}

					Query query=new Query();
					for(int i=0;i<transactions.size();i++){
						query.addCriteria(Criteria
								.where("trans_id")
								.is(transactions.get(i).getTrans_id())
								.and("user_id")
								.is(transactions.get(i).getUser_id()));
						
						Transaction _trans=mMongoTemplate.findOne(query, Transaction.class,DbConstraint.TABLE_PERSON);
						if(_trans == null){
							mMongoTemplate.save(_trans,DbConstraint.TABLE_PERSON);
							continue;
						}
						
						Update update=new Update();
						
						update.set("amount", _trans.getAmount());
						update.set("person", _trans.getPerson());
						update.set("note", _trans.getNote());
						update.set("image", _trans.getImage());
						update.set("type", _trans.getType());
						update.set("event", _trans.getEvent());
						update.set("category",_trans.getCategory());
						update.set("latitude", _trans.getLatitude());
						update.set("longitude", _trans.getLongitude());
						update.set("wallet", _trans.getWallet());
						update.set("date_created", _trans.getDate_created());
						update.set("date_end", _trans.getDate_end());
					
						mMongoTemplate.updateFirst(query, update,Transaction.class,DbConstraint.TABLE_PERSON);
					}
				
				}catch(MongoException e){
					throw new DatabaseException("Something wrong ! Please try later");
				}		
			}
		};
		doInBackGround.run();
	}
}
