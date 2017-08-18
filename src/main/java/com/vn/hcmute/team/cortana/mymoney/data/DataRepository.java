package com.vn.hcmute.team.cortana.mymoney.data;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;

import com.vn.hcmute.team.cortana.mymoney.bean.Person;

import com.vn.hcmute.team.cortana.mymoney.bean.Image;

import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.service.category.CategoryService;
import com.vn.hcmute.team.cortana.mymoney.data.service.currencies.CurrenciesService;
import com.vn.hcmute.team.cortana.mymoney.data.service.event.EventService;

import com.vn.hcmute.team.cortana.mymoney.data.service.person.PersonService;

import com.vn.hcmute.team.cortana.mymoney.data.service.image.ImageService;

import com.vn.hcmute.team.cortana.mymoney.data.service.saving.SavingService;
import com.vn.hcmute.team.cortana.mymoney.data.service.user.UserService;
import com.vn.hcmute.team.cortana.mymoney.data.service.wallet.WalletService;

@Repository

public class DataRepository implements DataSource.UserDataSource,
		DataSource.CurrenciesDataSource, DataSource.EventDataSource, 
		DataSource.CategorySource,DataSource.WalletDataSource,

		DataSource.SavingDataSource, DataSource.PersonDataSource,DataSource.ImageDataSource{

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

	//saving
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
	public void takeIn(String idWallet, String idSaving, String money) {
		savingService.takeIn(idWallet, idSaving, money);
		
	}

	@Override
	public void takeOut(String idWallet, String idSaving, String money) {
		savingService.takeOut(idWallet, idSaving, money);
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

	//person
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


	@Override
	public void initDefaultCategory(String userid) {
		mCategoryService.initDefaultCategory(userid);
		
	}

	@Override
	public List<Image> getAllImage(String userid) {
		return mImageService.getAllImage(userid);
	}

	@Override
	public void uploadImage(String userid, String token, String detail, InputStream input) {
		 mImageService.upload(userid, token, detail, input);
	}

	@Override
	public void removeImage(String userid, String imageId) {
		mImageService.remove(userid, imageId);
	}

	@Override
	public Image getImage(String userid, String imageId) {
		return mImageService.get(userid, imageId);

	}
}
