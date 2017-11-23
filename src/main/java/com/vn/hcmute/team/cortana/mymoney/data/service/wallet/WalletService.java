package com.vn.hcmute.team.cortana.mymoney.data.service.wallet;

import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import java.util.List;

public interface WalletService {
    
    List<Wallet> getInfoWallet(String userid);
    
    Wallet getWalletById(String wallet_id);
    
    void createWallet(Wallet wallet);
    
    void deleteWallet(String userid, String idwallet);
    
    void updateWallet(Wallet wallet);
    
    void moveMoneyWallet(String userid, String wallet_id_from, String wallet_id_to,
              String moneyMinus, String moneyPlus,
              String date_created);
    
    void syncWallet(List<Wallet> list);
    
    void takeOutWallet(String wallet_id, String money);
    
    void takeInWallet(String wallet_id, String money);
}
