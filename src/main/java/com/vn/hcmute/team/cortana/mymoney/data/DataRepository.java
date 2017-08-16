package com.vn.hcmute.team.cortana.mymoney.data;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.service.category.CategoryService;
import com.vn.hcmute.team.cortana.mymoney.data.service.currencies.CurrenciesService;
import com.vn.hcmute.team.cortana.mymoney.data.service.event.EventService;
import com.vn.hcmute.team.cortana.mymoney.data.service.user.UserService;
import com.vn.hcmute.team.cortana.mymoney.data.service.wallet.WalletService;

@Repository

public class DataRepository implements DataSource.UserDataSource,
		DataSource.CurrenciesDataSource, DataSource.EventDataSource, DataSource.CategorySource,DataSource.WalletDataSource {
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
	public List<Category> getDefaultCategory(String userid) {
		return mCategoryService.getDefaultCategory(userid);
	}

	@Override
	public List<Category> getCategoryByUserId(String userid) {
		return mCategoryService.getCategoryByUserId(userid);
	}

	@Override
	public void addCategory(Category category) {
		mCategoryService.addCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		mCategoryService.updateCategory(category);
	}

	@Override
	public void removeCategory(Category category) {
		mCategoryService.removeCategory(category);
	}

	@Override
	public boolean isApiKey(String userid, String token) {
		return mUserService.isApiKey(userid, token);
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
}
