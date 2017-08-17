package com.vn.hcmute.team.cortana.mymoney.data.service.saving;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Saving;

public interface SavingService {
	List<Saving> getSaving(String userid);
	void createSaving(Saving saving);
	void updateSaving(Saving saving);
	void deleteSaving(String idSaving);
	void takeIn(String idWallet, String idSaving,String money);
	void takeOut(String idWallet, String idSaving,String money);
}
