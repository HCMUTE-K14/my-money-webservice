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
			List<Transaction> list = mMongoTemplate.find(query(where("userId").is(userid)), Transaction.class,
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
			Transaction trans = mMongoTemplate.findOne(query(where("transactionId").is(transactionId).and("userId").is(userid)),
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
			List<Transaction> list = mMongoTemplate.find(query(where("type").is(type).and("userId").is(userid)),
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
			query.addCriteria(Criteria.where("dateCreate").gte(start).lte(end).and("userid").is(userid));
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
					query(where("categoryId").is(categoryId).and("userId").is(userid)), Transaction.class,
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
			update.set("eventId", transaction.getEventId());
			update.set("categoryId",transaction.getCategoryId());
			update.set("latitude", transaction.getLatitude());
			update.set("longtitude", transaction.getLongtitude());
			update.set("walletId", transaction.getWalletId());
			update.set("dateCreate", transaction.getDateCreate());
			update.set("dateEnd", transaction.getDateEnd());
			
			mMongoTemplate.updateFirst(query(where("transactionId").is(transaction.getTransactionId()).and("userId").is(transaction.getUserId())), 
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
			mMongoTemplate.findAndRemove(query(where("transactionId").is(transactionId).and("userId").is(userid)), Transaction.class, DbConstraint.TABLE_TRANSACTION);
			
		} catch (MongoException db) {
			throw new DatabaseException("Something wrong! please try later");
		}

	}

	@Override
	public List<Transaction> getTransactionByType(int type, String userid, String walletId) {
		try {
			List<Transaction> list = mMongoTemplate.find(query(where("type").is(type).and("userId").is(userid).and("walletId").is(walletId)),
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
			query.addCriteria(Criteria.where("dateCreate").gte(start).lte(end).and("userid").is(userid).and("walletId").is(walletId));
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
					query(where("categoryId").is(categoryId).and("userId").is(userid).and("walletId").is(walletId)), Transaction.class,
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
					String userid=transactions.get(0).getUserId();
					
					List<Transaction> listTransactionRemote=getAllTransaction(userid);
					
					for(int i=0; i<listTransactionRemote.size();i++){
						if(!transactions.contains(listTransactionRemote.get(i))){
							removeTransaction(
									listTransactionRemote.get(i).getTransactionId(),
									userid);
						}
					}

					Query query=new Query();
					for(int i=0;i<transactions.size();i++){
						query.addCriteria(Criteria
								.where("transactionId")
								.is(transactions.get(i).getTransactionId())
								.and("userid")
								.is(transactions.get(i).getUserId()));
						
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
						update.set("eventId", _trans.getEventId());
						update.set("categoryId",_trans.getCategoryId());
						update.set("latitude", _trans.getLatitude());
						update.set("longtitude", _trans.getLongtitude());
						update.set("walletId", _trans.getWalletId());
						update.set("dateCreate", _trans.getDateCreate());
						update.set("dateEnd", _trans.getDateEnd());
					
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
