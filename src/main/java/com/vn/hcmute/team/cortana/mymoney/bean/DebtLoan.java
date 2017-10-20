package com.vn.hcmute.team.cortana.mymoney.bean;

public class DebtLoan {
	private String debt_loan_id;
	private String note;
	private Transaction transaction;
	private String type; // "debt : no" "loan: cho ai muon";

	private int status; // 0,1; 1 da thanh toan
	private String user_id;

	public DebtLoan() {
		debt_loan_id = "";
		note = "";
		transaction = null;
		type = "";
		status = -1;
		user_id = "";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDebt_loan_id() {
		return debt_loan_id;
	}

	public void setDebt_loan_id(String debt_loan_id) {
		this.debt_loan_id = debt_loan_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debt_loan_id == null) ? 0 : debt_loan_id.hashCode());
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
		DebtLoan other = (DebtLoan) obj;
		if (debt_loan_id == null) {
			if (other.debt_loan_id != null)
				return false;
		} else if (!debt_loan_id.equals(other.debt_loan_id))
			return false;
		return true;
	}
}
