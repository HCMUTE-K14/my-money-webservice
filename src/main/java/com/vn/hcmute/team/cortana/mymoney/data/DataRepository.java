package com.vn.hcmute.team.cortana.mymoney.data;

import java.util.List;

import javax.xml.bind.DatatypeConverterInterface;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.service.currencies.CurrenciesService;
import com.vn.hcmute.team.cortana.mymoney.data.service.event.EventService;
import com.vn.hcmute.team.cortana.mymoney.data.service.user.UserService;
import com.vn.hcmute.team.cortana.mymoney.data.service.wallet.WalletService;

@Repository
public class DataRepository implements DataSource.UserDataSource,DataSource.WalletDataSource,
DataSource.CurrenciesDataSource, DataSource.EventDataSource{
	
	public static final Log LOG=LogFactory.getLog(DataRepository.class);
	
	private UserService mUserService;
	private WalletService mWalletService;
	private CurrenciesService currenciesService;
	@Autowired
	private EventService eventService;
	
	
	@Autowired
	public DataRepository( UserService userService,WalletService walletservice, CurrenciesService currenciesService){
		this.mUserService=userService;
		this.mWalletService=walletservice;
		this.currenciesService=currenciesService;
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
	//wallet
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
	//currencies
	@Override
	public List<Currencies> getCurrencies() {
		return currenciesService.getCurrencies();
	}
	//event
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

}
