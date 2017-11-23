package com.vn.hcmute.team.cortana.mymoney.bean;

public class Saving {
    
    private String saving_id;
    private String name;
    private String goal_money;
    private String start_money;
    private String current_money;
    private String date;
    private String wallet_id;
    private Currencies currencies;
    private String status;
    private String user_id;
    private String icon;
    
    public Saving() {
        this.saving_id = "";
        this.name = "";
        this.goal_money = "";
        this.start_money = "";
        this.current_money = "";
        this.date = "";
        this.wallet_id = "";
        this.currencies = new Currencies();
        this.status = "";
        this.user_id = "";
        this.icon = "";
    }
    
    public String getSaving_id() {
        return saving_id;
    }
    
    public void setSaving_id(String saving_id) {
        this.saving_id = saving_id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGoal_money() {
        return goal_money;
    }
    
    public void setGoal_money(String goal_money) {
        this.goal_money = goal_money;
    }
    
    public String getStart_money() {
        return start_money;
    }
    
    public void setStart_money(String start_money) {
        this.start_money = start_money;
    }
    
    public String getCurrent_money() {
        return current_money;
    }
    
    public void setCurrent_money(String current_money) {
        this.current_money = current_money;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getWallet_id() {
        return wallet_id;
    }
    
    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }
    
    public Currencies getCurrencies() {
        return currencies;
    }
    
    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
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
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @Override
    public String toString() {
        return "Saving [saving_id=" + saving_id + ", name=" + name + ", goal_money=" + goal_money +
               ", start_money="
               + start_money + ", current_money=" + current_money + ", date=" + date +
               ", wallet_id=" + wallet_id
               + ", currencies=" + currencies + ", status=" + status + ", user_id=" + user_id +
               ", icon=" + icon + "]";
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
        Saving other = (Saving) obj;
        if (saving_id == null) {
            if (other.saving_id != null) {
                return false;
            }
        } else if (!saving_id.equals(other.saving_id)) {
            return false;
        }
        return true;
    }
    
}
