package com.vn.hcmute.team.cortana.mymoney.data;

import java.util.List;


import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;

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
	}
	public interface WalletDataSource{
	
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
}
