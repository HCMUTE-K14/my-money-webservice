package com.vn.hcmute.team.cortana.mymoney.model;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalletModel {
    
    DataRepository dataRepository;
    
    @Autowired
    public WalletModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
    
    public void getInfoWallet(String userid, String token, CallBack<List<Wallet>> resultWallet) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                resultWallet.onFailure(new Throwable("Fail get Wallet!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                resultWallet.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Wallet> list = dataRepository.getInfoWallet(userid);
            resultWallet.onSuccess(list);
        } catch (Exception e) {
            resultWallet.onFailure(e);
        }
    }
    
    public void getWalletById(String userid, String token, String wallet_id,
              CallBack<Wallet> resultWallet) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                resultWallet.onFailure(new Throwable("Fail get Wallet!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                resultWallet.onFailure(new UserException("Wrong api key!"));
                return;
            }
            Wallet wallet = dataRepository.getWalletById(wallet_id);
            resultWallet.onSuccess(wallet);
        } catch (Exception e) {
            resultWallet.onFailure(e);
        }
    }
    
    public void createWallet(Wallet wallet, String userid, String token,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get Wallet check token!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.createWallet(wallet);
            callBack.onSuccess("Success create wallet");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void deleteWallet(String userid, String token, String idwallet,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get wallet check token!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.deleteWallet(userid, idwallet);
            callBack.onSuccess("Success delete wallet");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
        
    }
    
    public void updateWallet(Wallet wallet, String userid, String token,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get wallet!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.updateWallet(wallet);
            callBack.onSuccess("Success update wallet");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void moveMoneyWallet(String userid, String token, String wallet_id_from,
              String wallet_id_to,
              String moneyMinus, String moneyPlus, String date_created, CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get wallet check token!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository
                      .moveMoneyWallet(userid, wallet_id_from, wallet_id_to, moneyMinus, moneyPlus,
                                date_created);
            callBack.onSuccess("Transfer money successful");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void syncWallet(List<Wallet> list, String userid, String token,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail sync wallet!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.syncWallet(list);
            callBack.onSuccess("Success sync wallet");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
}
