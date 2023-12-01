package fenn.bank.account.dto;

import java.math.BigDecimal;

public class AccountInfo {

	private  String customerID;
	private  BigDecimal initialCredit;

	public AccountInfo() {
	}
	public AccountInfo(String accountId, BigDecimal initialCredit) {
		this.customerID = accountId;
		this.initialCredit = initialCredit;
	}

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public BigDecimal getInitialCredit() {
		return initialCredit;
	}
	public void setInitialCredit(BigDecimal initialCredit) {
		this.initialCredit = initialCredit;
	}

	public String toString() {
		return "customerID :".concat(this.customerID).concat("### initialCredit ").concat(this.initialCredit.toString());
	}
}
