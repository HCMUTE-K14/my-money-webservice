package com.vn.hcmute.team.cortana.mymoney.model;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BudgetModel {
    
    @Autowired
    DataRepository dataRepository;
    
    public void getBudget(String userid, String token, CallBack<List<Budget>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get Budget!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Budget> list = dataRepository.getBudget(userid);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail get Budget!"));
        }
    }
    
    public void createBudget(Budget budget, String userid, String token,
              CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail create Budget!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            // event.setId(SecurityUtil.);
            dataRepository.createBudget(budget);
            callBack.onSuccess("Success create event");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail create Budget!"));
        }
    }
    
    public void updateBudget(Budget budget, String userid, String token,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail update Budget!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.updateBudget(budget);
            callBack.onSuccess("Success update budget");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail update budget!"));
        }
    }
    
    public void deleteBudget(String userid, String token, String budgetId,
              CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail delete budget!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.removeBudget(budgetId);
            callBack.onSuccess("Success delete budget");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail delete budget!"));
        }
    }
    
    public void syncBudget(List<Budget> list, String userid, String token,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail sync Budget!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.syncBudget(list);
            callBack.onSuccess("Success sync budget");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail sync budget!"));
        }
    }
}
