package com.vn.hcmute.team.cortana.mymoney.bean;

public class Event {

    private String event_id;
    private String name;
    private String money;
    private String date;
    private String wallet_id;
    private String status;
    private String user_id;
    private Currencies currencies;
    private String icon;

    public Event() {
        this.event_id = "";
        this.name = "";
        this.money = "";
        this.date = "";
        this.event_id = "";
        this.status = "";
        this.event_id = "";
        this.icon = "";
        this.currencies = new Currencies();
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Event [event_id=" + event_id + ", name=" + name + ", money=" + money + ", date=" +
               date + ", wallet_id="
               + wallet_id + ", status=" + status + ", user_id=" + user_id + ", currencies=" +
               currencies + ", icon="
               + icon + "]";
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
        Event other = (Event) obj;
        if (event_id == null) {
            if (other.event_id != null) {
                return false;
            }
        } else if (!event_id.equals(other.event_id)) {
            return false;
        }
        return true;
    }

}
