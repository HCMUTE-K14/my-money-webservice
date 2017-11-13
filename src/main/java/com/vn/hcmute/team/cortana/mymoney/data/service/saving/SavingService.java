package com.vn.hcmute.team.cortana.mymoney.data.service.saving;

import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import java.util.List;

public interface SavingService {
    
    List<Saving> getSaving(String userid);
    
    void createSaving(Saving saving);
    
    void updateSaving(Saving saving);
    
    void deleteSaving(String idSaving);
    
    void takeIn(String idWallet, String idSaving, String moneyUpdateWallet,
              String moneyUpdateSaving);
    
    void takeOut(String idWallet, String idSaving, String moneyUpdateWallet,
              String moneyUpdateSaving);
    
    void syncSaving(List<Saving> list);
}
