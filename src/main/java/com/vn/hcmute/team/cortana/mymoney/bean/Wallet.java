package com.vn.hcmute.team.cortana.mymoney.bean;

public class Wallet {
	private String walletid;
	public String getWalletid() {
		return walletid;
	}
	public void setWalletid(String walletid) {
		this.walletid = walletid;
	}

	private String userid;
	private String walletName;
	private String money;
	private Currencies currencyUnit;
	private String walletImage;
	private boolean archive;
	
	public String getWalletImage() {
		return walletImage;
	}
	public void setWalletImage(String walletImage) {
		this.walletImage = walletImage;
	}
	public Wallet() {
		this.walletid="";
		this.userid="";
		this.walletName="";
		this.money="";
		this.currencyUnit=new Currencies();
		this.archive=false;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWalletName() {
		return walletName;
	}
	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Currencies getCurrencyUnit() {
		return currencyUnit;
	}
	public void setCurrencyUnit(Currencies currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	@Override
	public String toString() {
		return "Wallet[id= "+walletid+",userid= "+userid+",walletname="+
				walletName+",money="+money+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		if (walletid == null) {
			if (other.walletid != null)
				return false;
		} else if (!walletid.equals(other.walletid))
			return false;
		return true;
	}
	
	
}
