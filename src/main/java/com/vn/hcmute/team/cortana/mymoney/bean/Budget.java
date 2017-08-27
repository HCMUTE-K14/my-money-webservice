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
}
