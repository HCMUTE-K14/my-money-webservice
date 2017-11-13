package com.vn.hcmute.team.cortana.mymoney.data.service.budget;

import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
import java.util.List;

public interface BudgetService {
    
    List<Budget> getBudget(String userid);
    
    void createBudget(Budget budget);
    
    void updateBudget(Budget budget);
    
    void removeBudget(String budgetId);
    
    void syncBudget(List<Budget> list);
    
}
