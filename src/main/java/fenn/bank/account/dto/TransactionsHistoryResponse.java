package fenn.bank.account.dto;

import java.util.List;

public class TransactionsHistoryResponse {
 private List<Transaction> transactionList;

public TransactionsHistoryResponse() {
	//Just for the sake of making sonar happy.
}

public List<Transaction> getTransactionList() {
	return transactionList;
}

public void setTransactionList(List<Transaction> transactionList) {
	this.transactionList = transactionList;
}

}
