package com.vn.hcmute.team.cortana.mymoney.bean;

public class Budget {
	private String budgetId;
	private String categoryId;
	private String walletid;
	private String rangeDate;
	private String moneyGoal;
	private String status;
	private String userid;
	private String moneyExpense;
	public String getMoneyGoal() {
		return moneyGoal;
	}
	public void setMoneyGoal(String moneyGoal) {
		this.moneyGoal = moneyGoal;
	}
	public String getMoneyExpense() {
		return moneyExpense;
	}
	public void setMoneyExpense(String moneyExpense) {
		this.moneyExpense = moneyExpense;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getWalletid() {
		return walletid;
	}
	public void setWalletid(String walletid) {
		this.walletid = walletid;
	}
	public String getRangeDate() {
		return rangeDate;
	}
	public void setRangeDate(String rangeDate) {
		this.rangeDate = rangeDate;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((budgetId == null) ? 0 : budgetId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Budget other = (Budget) obj;
		if (budgetId == null) {
			if (other.budgetId != null)
				return false;
		} else if (!budgetId.equals(other.budgetId))
			return false;
		return true;
	}
	
}
