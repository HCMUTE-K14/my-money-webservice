package com.vn.hcmute.team.cortana.mymoney.bean;

public class Budget {
    
    private String budget_id;
    private String range_date;
    private String money_goal;
    private String status;
    private String user_id;
    private String money_expense;
    private Wallet wallet;
    private Category category;
    
    public Budget() {
        this.budget_id = "";
        this.range_date = "";
        this.money_goal = "";
        this.status = "";
        this.user_id = "";
        this.money_expense = "";
        this.wallet = new Wallet();
        this.category = new Category();
    }
    
    public String getBudget_id() {
        return budget_id;
    }
    
    public void setBudget_id(String budget_id) {
        this.budget_id = budget_id;
    }
    
    public String getRange_date() {
        return range_date;
    }
    
    public void setRange_date(String range_date) {
        this.range_date = range_date;
    }
    
    public String getMoney_goal() {
        return money_goal;
    }
    
    public void setMoney_goal(String money_goal) {
        this.money_goal = money_goal;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getUser_id() {
        return user_id;
    }
    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    public String getMoney_expense() {
        return money_expense;
    }
    
    public void setMoney_expense(String money_expense) {
        this.money_expense = money_expense;
    }
    
    public Wallet getWallet() {
        return wallet;
    }
    
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return "Budget [budget_id=" + budget_id + ", range_date=" + range_date + ", money_goal=" +
               money_goal
               + ", status=" + status + ", user_id=" + user_id + ", money_expense=" +
               money_expense + ", wallet="
               + wallet + ", category=" + category + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((budget_id == null) ? 0 : budget_id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Budget other = (Budget) obj;
        if (budget_id == null) {
            if (other.budget_id != null) {
                return false;
            }
        } else if (!budget_id.equals(other.budget_id)) {
            return false;
        }
        return true;
    }
    
}
