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
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.data.service.transaction.TransactionService;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class WalletServiceImp implements WalletService {
	public static final Log LOG = LogFactory.getLog(WalletServiceImp.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	TransactionService mTransactionService;

	public final Category WITH_DRAW_CATEGORY = new Category("48", "Withdrawal", "icon_withdrawal", "expense",
			"expense");

	public final Category GIFT_CATEGORY = new Category("53", "Gift", "ic_category_give", "income", "income");

	public WalletServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Wallet> getInfoWallet(String userid) {
		try {
			Query searchQuery = new Query(Criteria.where("user_id").is(userid));

			return mongoTemplate.find(searchQuery, Wallet.class, DbConstraint.TABLE_WALLET);
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public Wallet getWalletById(String wallet_id) {
		try {
			Query query = new Query(Criteria.where("wallet_id").is(wallet_id));

			return mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void createWallet(Wallet wallet) {
		try {

			LOG.info("Insert document...");
			Query query = new Query();
			query.addCriteria(
					Criteria.where("wallet_id").is(wallet.getWallet_id()).and("user_id").is(wallet.getUser_id()));
			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);
			if (wallet2 != null) {
				throw new RuntimeException("Wallet exists");
			}
			mongoTemplate.save(wallet, DbConstraint.TABLE_WALLET);
			LOG.info("Insert successful...");
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}

	}

	@Override
	public void deleteWallet(String userid, String idwallet) {
		try {
			LOG.info("Delete document...");
			Query query = new Query();
			query.addCriteria(Criteria.where("wallet_id").is(idwallet).and("user_id").is(userid));
			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);
			if (wallet2 == null)
				throw new RuntimeException("Null Wallet");
			// Budget,Saving,Event,Transaction,DebtLoan
			mongoTemplate.remove(query(where("wallet.wallet_id").is(idwallet)), DbConstraint.TABLE_BUDGET);
			mongoTemplate.remove(query(where("wallet.wallet_id").is(idwallet)), DbConstraint.TABLE_SAVING);
			mongoTemplate.remove(query(where("wallet.wallet_id").is(idwallet)), DbConstraint.TABLE_EVENT);
			mongoTemplate.remove(query(where("wallet.wallet_id").is(idwallet)), DbConstraint.TABLE_TRANSACTION);
			mongoTemplate.remove(query(where("transaction.wallet.wallet_id").is(idwallet)),
					DbConstraint.TABLE_DEBT_LOAN);

			mongoTemplate.remove(query, Wallet.class, DbConstraint.TABLE_WALLET);
			LOG.info("Delete successful...");
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}

	}

	@Override
	public void updateWallet(Wallet wallet) {
		try {
			Query query = new Query();
			System.out.println(wallet.getWallet_id());
			query.addCriteria(Criteria.where("wallet_id").is(wallet.getWallet_id()));

			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);

			if (wallet2 == null)
				throw new RuntimeException("Null wallet!");

			Update update = new Update();

			update.set("name", wallet.getName());
			update.set("money", wallet.getMoney());
			update.set("currency", wallet.getCurrency());
			update.set("icon", wallet.getIcon());
			update.set("archive", wallet.isArchive());

			mongoTemplate.updateFirst(query, update, Wallet.class, DbConstraint.TABLE_WALLET);

		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}

	}

	@Override
	public void syncWallet(List<Wallet> list) {
		try {
			if (list == null) {
				throw new RuntimeException("null list");
			}

			List<Wallet> listWallet = getInfoWallet(list.get(0).getUser_id());

			for (int i = 0; i < listWallet.size(); i++) {
				if (!list.contains(listWallet.get(i))) {
					deleteWallet(listWallet.get(i).getUser_id(), listWallet.get(i).getWallet_id());
				}
			}

			for (Wallet wallet : list) {
				sync(wallet);
			}

		} catch (MongoException ex) {
			throw new DatabaseException("Something wrong! Please try later");
		}

	}

	@Override
	public void moveMoneyWallet(String userid, String wallet_id_from, String wallet_id_to, String moneyMinus, String moneyPlus, String date_created) {
		try {
			addTransaction(0,userid,wallet_id_from,moneyMinus,date_created);
			
			addTransaction(1,userid,wallet_id_to,moneyPlus,date_created);
		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}
	
	private void addTransaction(int type,String userid, String wallet_id, String amount, String date_created){
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("wallet_id").is(wallet_id));

			Wallet walletFrom = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);

			Transaction transaction = prepareTransaction(type, date_created);

			transaction.setWallet(walletFrom);
			transaction.setUser_id(userid);
			transaction.setAmount(amount);

			mTransactionService.addTransaction(transaction);
		}catch(MongoException ex){
			throw new DatabaseException("Something wrong! Please try later");
		}catch(Exception ex){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	/**
	 * 0: wallet from 
	 * 1: wallet to
	 */
	private Transaction prepareTransaction(int type, String date_created) {

		Transaction transaction = new Transaction();
		
		transaction.setTrans_id(SecurityUtil.generateUUID());

		transaction.setDate_created(Long.valueOf(date_created));
		if (type == 0) {
			transaction.setCategory(WITH_DRAW_CATEGORY);
			transaction.setType("expense");
		} else if (type == 1) {
			transaction.setCategory(GIFT_CATEGORY);
			transaction.setType("income");
		}

		return transaction;
	}

	private void sync(Wallet wallet) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("wallet_id").is(wallet.getWallet_id()));

			Wallet wallet2 = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);

			if (wallet2 == null) {
				createWallet(wallet);
				return;

			}

			Update update = new Update();
			update.set("name", wallet.getName());
			update.set("money", wallet.getMoney());
			update.set("currency", wallet.getCurrency());
			update.set("icon", wallet.getIcon());

			mongoTemplate.updateFirst(query, update, Wallet.class, DbConstraint.TABLE_WALLET);

		} catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void takeOutWallet(String wallet_id, String money) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("wallet_id").is(wallet_id));
			Wallet wallet = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);

			double _money = Double.valueOf(wallet.getMoney()) - Double.valueOf(money);
			Update update = new Update();
			update.set("money", String.valueOf(_money));

			mongoTemplate.updateFirst(query, update, Wallet.class, DbConstraint.TABLE_WALLET);

		} catch (Exception e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void takeInWallet(String wallet_id, String money) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("wallet_id").is(wallet_id));
			Wallet wallet = mongoTemplate.findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);

			double _money = Double.valueOf(wallet.getMoney()) + Double.valueOf(money);
			Update update = new Update();
			update.set("money", String.valueOf(_money));

			mongoTemplate.updateFirst(query, update, Wallet.class, DbConstraint.TABLE_WALLET);

		} catch (Exception e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

}
