package com.vn.hcmute.team.cortana.mymoney.data;

import java.util.List;


import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.bean.Person;
import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.bean.Category;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;

public class DataSource {
	public interface UserDataSource {
		void register(User user);

		long getNumberOfActiveUser();
		
		User login(UserCredential userCredential);
		
		boolean isApiKey(String userid,String token);
		
		void forgetPassword(String email);
		
		void changePassword(String userid,String oldpassword,String newpassword);
		
		void changeProfile(User user);
	}

	public interface CategorySource{
		List<Category> getDefaultCategory(String userid);
		
		List<Category> getCategoryByUserId(String userid);
		
		void addCategory(Category category);
		
		void updateCategory(Category category);
		
		void removeCategory(Category category);
		
	}
	public interface WalletDataSource{
		List<Wallet> getInfoWallet(String userid);
		void createWallet(Wallet wallet);
		void deleteWallet(String userid, String idwallet);
		void updateWallet(Wallet wallet);
		void moveMoneyWallet(String userid, String idWalletFrom, String idWallet, String money);
	}
	public interface CurrenciesDataSource {
		List<Currencies> getCurrencies();
	}
	public interface EventDataSource{
		List<Event> getEvent(String userid);
		void createEvent(Event event);
		void updateEvent(Event event);
		void deleteEvent(String userid, String idEvent);
	}
	public interface SavingDataSource{
		List<Saving> getSaving(String userid);
		void createSaving(Saving saving);
		void updateSaving(Saving saving);
		void deleteSaving(String idSaving);
		void takeIn(String idWallet, String idSaving,String money);
		void takeOut(String idWallet, String idSaving,String money);
	}
	public interface PersonDataSource{
		List<Person> getPersons(String userid);
		void addPerson(Person person);
		void removePerson(String personid);
	}
}
