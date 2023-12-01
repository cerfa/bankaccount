package fenn.bank.account.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountDeepInfo {
	private String name;
	private String surname; 
	private BigDecimal balance;
	private List<Transaction> transactionCollection;
		
	public AccountDeepInfo() {
	}
	public AccountDeepInfo(String name, String surname, BigDecimal balance, List<Transaction> transactionCollection) {
		this.name = name;
		this.surname = surname;
		this.balance = balance;
		this.transactionCollection = transactionCollection;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Transaction> getTransactionCollection() {
		return transactionCollection;
	}
	public void setTransactionCollection(List<Transaction> transactionCollection) {
		this.transactionCollection = transactionCollection;
	}

}
