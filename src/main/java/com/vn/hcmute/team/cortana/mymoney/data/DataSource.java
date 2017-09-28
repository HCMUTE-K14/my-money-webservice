package com.vn.hcmute.team.cortana.mymoney.data;

import java.io.InputStream;
import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;

import com.vn.hcmute.team.cortana.mymoney.bean.Person;

import com.vn.hcmute.team.cortana.mymoney.bean.Image;

import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;

public interface DataSource {
	public interface UserDataSource {
		void register(User user);

		long getNumberOfActiveUser();
		
		User login(UserCredential userCredential);
		
		boolean isApiKey(String userid,String token);
		
		void forgetPassword(String email);
		
		void changePassword(String userid,String oldpassword,String newpassword);
		
		void changeProfile(User user);
		
		User loginWithFacebook(User user);
		
		boolean isExistFacebookAccount(String email);
	}

	public interface CategorySource{
		
		List<Category> getCategoryByUserId(String userid);
		
		void addCategory(Category category,String parentId);
		
		void updateCategory(Category category,String oldParentId,String parentId);
		
		void removeCategory(Category category,String parentId);
		
		void initDefaultCategory(String userid);
 
		List<Category> getCategoryByTransactionType(String userid,String transactionType);
		
		List<Category> getCategoryByType(String userid,String type,String transType);

	}
	public interface WalletDataSource{
		List<Wallet> getInfoWallet(String userid);
		
		void createWallet(Wallet wallet);
		
		void deleteWallet(String userid, String idwallet);
		
		void updateWallet(Wallet wallet);
		
		void moveMoneyWallet(String userid, String idWalletFrom, String idWallet, String money);
		
		void syncWallet(List<Wallet> list);
	}
	public interface CurrenciesDataSource {
		List<Currencies> getCurrencies();
	}
	public interface EventDataSource{
		List<Event> getEvent(String userid);
		
		void createEvent(Event event);
		
		void updateEvent(Event event);
		
		void deleteEvent(String userid, String idEvent);
		
		void syncEvent(List<Event> list);
	}
	public interface SavingDataSource{
		List<Saving> getSaving(String userid);
		
		void createSaving(Saving saving);
		
		void updateSaving(Saving saving);
		
		void deleteSaving(String idSaving);
		
		void takeIn(String idWallet, String idSaving,String money);
		
		void takeOut(String idWallet, String idSaving,String money);
		
		void syncSaving(List<Saving> list);
	}

	public interface PersonDataSource{
		List<Person> getPersons(String userid);
		
		void addPerson(Person person);
		
		void updatePerson(Person person);
		
		void removePerson(String personid);
		
		void synchPerson(List<Person> persons);
	}
	public interface ImageDataSource{
		List<Image> getAllImage(String userid);
		
		void uploadImage(String userid,String token,String detail,InputStream input);
		
		void removeImage(String userid,String imageId);
		
		Image getImage(String userid,String imageId);

	}
	public interface TransactionDataSource{
		List<Transaction> getAllTransaction(String userid);
		
		Transaction  getTransactionById(String transactionId,String userid);
		
		List<Transaction> getTransactionByType(int type,String userid); //cho vay, chi tieu, thu nhap
		
		List<Transaction> getTransactionByTime(String startDate,String endDate,String userid);

		List<Transaction> getTransactionByCategory(String categoryId,String userid);
		
		List<Transaction> getTransactionByType(int type,String userid,String walletId); //cho vay, chi tieu, thu nhap
		
		List<Transaction> getTransactionByTime(String startDate,String endDate,String userid,String walletId);

		List<Transaction> getTransactionByCategory(String categoryId,String userid,String walletId);
		
		void addTransaction(Transaction transaction);
		
		void updateTransaction(Transaction transaction);
		
		void removeTransaction(String transactionId,String userid);
		
		void syncTransaction(List<Transaction> transactions);
	}
	public interface BudgetDataSource{
		List<Budget> getBudget(String userid);
		
		void createBudget(Budget budget);
		
		void updateBudget(Budget budget);
		
		void removeBudget(String budgetId);
		
		void syncBudget(List<Budget> list);
	}
}
