package com.vn.hcmute.team.cortana.mymoney.data.service.budget;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Budget;

public interface BudgetService {
	List<Budget> getBudget(String userid);
	void createBudget(Budget budget);
	void updateBudget(Budget budget);
	void removeBudget(String budgetId);
	
}
