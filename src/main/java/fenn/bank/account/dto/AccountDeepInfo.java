package fenn.bank.account.dto;

import fenn.bank.account.entities.Account;

import java.math.BigDecimal;
import java.util.List;

public class AccountDeepInfo {
	private String name;
	private String surname; 
	private BigDecimal balance;
	private List<Transaction> transactionCollection;
	private List<Account> accountCollection;
	public AccountDeepInfo() {
		//Just for the sake of making sonar happy.
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
	public List<Account> getAccountCollection() {
		return accountCollection;
	}
	public void setAccountCollection(List<Account> accountCollection) {
		this.accountCollection = accountCollection;
	}
}
