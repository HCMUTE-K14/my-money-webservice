package com.vn.hcmute.team.cortana.mymoney.data;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.DebtLoan;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;

import com.vn.hcmute.team.cortana.mymoney.bean.Person;

import com.vn.hcmute.team.cortana.mymoney.bean.Image;

import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.service.budget.BudgetService;
import com.vn.hcmute.team.cortana.mymoney.data.service.category.CategoryService;
import com.vn.hcmute.team.cortana.mymoney.data.service.currencies.CurrenciesService;
import com.vn.hcmute.team.cortana.mymoney.data.service.debtloan.DebtLoanService;
import com.vn.hcmute.team.cortana.mymoney.data.service.event.EventService;

import com.vn.hcmute.team.cortana.mymoney.data.service.person.PersonService;

import com.vn.hcmute.team.cortana.mymoney.data.service.image.ImageService;

import com.vn.hcmute.team.cortana.mymoney.data.service.saving.SavingService;
import com.vn.hcmute.team.cortana.mymoney.data.service.transaction.TransactionService;
import com.vn.hcmute.team.cortana.mymoney.data.service.user.UserService;
import com.vn.hcmute.team.cortana.mymoney.data.service.wallet.WalletService;

@Repository

public class DataRepository implements DataSource.UserDataSource, DataSource.CurrenciesDataSource,
		DataSource.EventDataSource, DataSource.CategorySource, DataSource.WalletDataSource, DataSource.SavingDataSource,
		DataSource.PersonDataSource, DataSource.ImageDataSource, DataSource.TransactionDataSource , DataSource.BudgetDataSource, DataSource.DebtLoanSource{

	public static final Log LOG = LogFactory.getLog(DataRepository.class);

	@Autowired
	private UserService mUserService;
	@Autowired
	private WalletService mWalletService;
	@Autowired
	private CurrenciesService currenciesService;
	@Autowired
	private EventService eventService;
	@Autowired
	private CategoryService mCategoryService;
	@Autowired
	private SavingService savingService;
	@Autowired
	private PersonService personService;
	@Autowired
	private ImageService mImageService;
	@Autowired
	private TransactionService mTransactionService;
	@Autowired
	private BudgetService mBudgetService;
	
	@Autowired
	private DebtLoanService mDebtLoanService;

	public DataRepository() {
		LOG.info("DataRepository is created");
	}

	@Override
	public void register(User user) {
		this.mUserService.register(user);
	}

	@Override
	public long getNumberOfActiveUser() {
		return this.mUserService.getNumberOfActiveUser();
	}

	@Override
	public User login(UserCredential userCredential) {
		return this.mUserService.login(userCredential);
	}

	// wallet
	@Override
	public List<Wallet> getInfoWallet(String userid) {
		return mWalletService.getInfoWallet(userid);
	}

	@Override
	public void createWallet(Wallet wallet) {
		mWalletService.createWallet(wallet);
	}

	@Override
	public void deleteWallet(String userid, String idwallet) {
		mWalletService.deleteWallet(userid, idwallet);
	}

	@Override
	public void updateWallet(Wallet wallet) {
		mWalletService.updateWallet(wallet);
	}

	@Override
	public void moveMoneyWallet(String userid, String idWalletFrom, String idWallet, String money) {
		mWalletService.moveMoneyWallet(userid, idWalletFrom, idWallet, money);

	}

	// currencies
	@Override
	public List<Currencies> getCurrencies() {
		return currenciesService.getCurrencies();
	}

	// event
	@Override
	public List<Event> getEvent(String userid) {

		return eventService.getEvent(userid);
	}

	@Override
	public void createEvent(Event event) {
		eventService.createEvent(event);

	}

	@Override
	public void updateEvent(Event event) {
		eventService.updateEvent(event);

	}

	@Override
	public void deleteEvent(String userid, String idEvent) {
		eventService.deleteEvent(userid, idEvent);

	}

	@Override
	public List<Category> getCategoryByUserId(String userid) {
		return mCategoryService.getCategoryByUserId(userid);
	}

	@Override
	public void addCategory(Category category,String parentId) {
		mCategoryService.addCategory(category,parentId);
	}

	@Override
	public void updateCategory(Category category,String oldParentId,String newParentId) {
		mCategoryService.updateCategory(category,oldParentId,newParentId);
	}

	@Override
	public void removeCategory(Category category,String parentId) {
		mCategoryService.removeCategory(category,parentId);
	}

	@Override
	public boolean isApiKey(String userid, String token) {
		return mUserService.isApiKey(userid, token);
	}

	// saving
	@Override
	public List<Saving> getSaving(String userid) {

		return savingService.getSaving(userid);
	}

	@Override
	public void createSaving(Saving saving) {
		savingService.createSaving(saving);

	}

	@Override
	public void updateSaving(Saving saving) {
		savingService.updateSaving(saving);

	}

	@Override
	public void deleteSaving(String idSaving) {
		savingService.deleteSaving(idSaving);

	}

	@Override
	public void takeIn(String idWallet, String idSaving,String moneyUpdateWallet,String moneyUpdateSaving) {
		savingService.takeIn(idWallet, idSaving, moneyUpdateWallet,moneyUpdateSaving);
	}

	@Override
	public void takeOut(String idWallet, String idSaving,String moneyUpdateWallet,String moneyUpdateSaving) {
		savingService.takeOut(idWallet, idSaving, moneyUpdateWallet,moneyUpdateSaving);
	}

	@Override
	public void forgetPassword(String email) {
		mUserService.forgetPassword(email);
	}

	@Override
	public void changePassword(String userid, String oldpassword, String newpassword) {
		mUserService.changePassword(userid, oldpassword, newpassword);

	}

	@Override
	public void changeProfile(User user) {
		mUserService.changeProfile(user);

	}

	// person
	@Override
	public List<Person> getPersons(String userid) {
		return personService.getPersons(userid);
	}

	@Override
	public void addPerson(Person person) {
		personService.addPerson(person);

	}

	@Override
	public void removePerson(String personid) {
		personService.removePerson(personid);

	}

	@Override
	public void initDefaultCategory(String userid) {
		mCategoryService.initDefaultCategory(userid);

	}

	@Override
	public List<Image> getAllImage(String userid) {
		return mImageService.getAllImage(userid);
	}

	@Override
	public void uploadImage(String userid, String token, String detail,InputStream input) {
		mImageService.upload(userid, token, detail, input);
	}
	@Override
	public List<Image> uploadImage(String userid, String token, String detail,InputStream[] input) {
		return mImageService.upload(userid, token, detail, input);
	}


	@Override
	public void removeImage(String userid, String imageId) {
		mImageService.remove(userid, imageId);
	}

	@Override
	public Image getImage(String userid, String imageId) {
		return mImageService.get(userid, imageId);

	}

	@Override
	public List<Transaction> getAllTransaction(String userid) {
		return mTransactionService.getAllTransaction(userid);
	}

	@Override
	public Transaction getTransactionById(String transactionId,String userid) {
		return mTransactionService.getTransactionById(transactionId,userid);
	}

	@Override
	public List<Transaction> getTransactionByType(int type, String userid) {
		return mTransactionService.getTransactionByType(type, userid);
	}

	@Override
	public List<Transaction> getTransactionByTime(String startDate, String endDate, String userid) {
		return mTransactionService.getTransactionByTime(startDate, endDate, userid);
	}

	@Override
	public List<Transaction> getTransactionByCategory(String categoryId, String userid) {
		return mTransactionService.getTransactionByCategory(categoryId, userid);
	}

	@Override
	public List<Transaction> getTransactionByType(int type, String userid, String walletId) {
		return mTransactionService.getTransactionByType(type, userid, walletId);
	}

	@Override
	public List<Transaction> getTransactionByTime(String startDate, String endDate, String userid, String walletId) {
		return mTransactionService.getTransactionByTime(startDate, endDate, userid, walletId);
	}

	@Override
	public List<Transaction> getTransactionByCategory(String categoryId, String userid, String walletId) {
		return mTransactionService.getTransactionByCategory(categoryId, userid, walletId);
	}

	@Override
	public void addTransaction(Transaction transaction) {
		mTransactionService.addTransaction(transaction);
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		mTransactionService.updateTransaction(transaction);
	}

	@Override
	public void removeTransaction(String transactionId, String userid) {
		mTransactionService.removeTransaction(transactionId, userid);
	}
	//budget
	@Override
	public List<Budget> getBudget(String userid) {
		return mBudgetService.getBudget(userid);
	}

	@Override
	public void createBudget(Budget budget) {
		mBudgetService.createBudget(budget);
	}

	@Override
	public void updateBudget(Budget budget) {
		mBudgetService.updateBudget(budget);
	}

	@Override
	public void removeBudget(String budgetId) {
		mBudgetService.removeBudget(budgetId);
	}

	@Override
	public void updatePerson(Person person) {
		personService.updatePerson(person);
	}

	@Override
	public void synchPerson(List<Person> persons) {
		personService.syncPerson(persons);
	}

	@Override
	public void syncTransaction(List<Transaction> transactions) {
		mTransactionService.syncTransaction(transactions);
	}

	@Override
	public void syncBudget(List<Budget> list) {
		
		mBudgetService.syncBudget(list);
	}

	@Override
	public void syncEvent(List<Event> list) {
		
		eventService.syncEvent(list);
	}

	@Override
	public void syncSaving(List<Saving> list) {
		savingService.syncSaving(list);
		
	}

	@Override
	public void syncWallet(List<Wallet> list) {
		mWalletService.syncWallet(list);
	}
	

	@Override
	public List<Category> getCategoryByTransactionType(String userid,String transactionType) {
		return mCategoryService.getCategoryByTransactionType(userid, transactionType);
	}

	@Override
	public List<Category> getCategoryByType(String userid, String type,String transType) {
		return mCategoryService.getCategoryByType(userid, type,transType);
	}

	@Override
	public User loginWithFacebook(User user) {
		return mUserService.loginWithFacebook(user);
	}

	@Override
	public boolean isExistFacebookAccount(String email) {
		return mUserService.isExistFacebookAccount(email);
	}

	@Override
	public void takeInWallet(String wallet_id, String money) {
		 mWalletService.takeInWallet(wallet_id, money);
	}

	@Override
	public void takeOutWallet(String wallet_id, String money) {
		 mWalletService.takeOutWallet(wallet_id, money);
	}

	@Override
	public List<DebtLoan> getDebtLoan(String wallet_id) {
		return mDebtLoanService.getDebtLoan(wallet_id);
	}

	@Override
	public void addDebtLoan(DebtLoan debtLoan) {
		mDebtLoanService.addDebtLoan(debtLoan);
	}

	@Override
	public void updateDebtLoan(DebtLoan debtLoan, String wallet_id) {
		mDebtLoanService.updateDebtLoan(debtLoan, wallet_id);
	}

	@Override
	public void deleteDebtLoan(DebtLoan debtLoan) {
		mDebtLoanService.deleteDebtLoan(debtLoan);
	}

	@Override
	public List<DebtLoan> getDebtLoanByType(String wallet_id, String type) {
		return mDebtLoanService.getDebtLoanByType(wallet_id, type);
	}
}
