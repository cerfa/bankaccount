package fenn.bank.account.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
	private Integer transactionId;
	private String accountId;
	private BigDecimal amount;
	private Timestamp timeStamp;

	public Transaction() {
		//Just for the sake of making sonar happy.
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
