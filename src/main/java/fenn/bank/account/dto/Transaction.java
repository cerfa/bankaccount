package fenn.bank.account.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
	private Integer transactionId;
	private BigDecimal amount;
	private Timestamp timeStamp;


	public Transaction() {
	}
	public Transaction(Integer transactionId, BigDecimal amount, Timestamp timeStamp) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.timeStamp = timeStamp;
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

}
