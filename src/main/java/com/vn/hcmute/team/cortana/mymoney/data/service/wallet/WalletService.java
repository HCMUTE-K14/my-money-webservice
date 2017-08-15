package com.vn.hcmute.team.cortana.mymoney.data.service.wallet;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;

public interface WalletService {
	List<Wallet> getInfoWallet(String userid);
	void createWallet(Wallet wallet);
	void deleteWallet(String userid, String idwallet);
	void updateWallet(Wallet wallet);
	void moveMoneyWallet(String userid, String idWalletFrom, String idWallet, String money);
}
