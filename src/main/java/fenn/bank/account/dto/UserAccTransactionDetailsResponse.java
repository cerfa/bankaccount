package fenn.bank.account.dto;

public class UserAccTransactionDetailsResponse {
 private AccountDeepInfo userAccountDetails;

public UserAccTransactionDetailsResponse() {
	//Just for the sake of making sonar happy.
}

public UserAccTransactionDetailsResponse(AccountDeepInfo userAccountDetails) {
	this.userAccountDetails = userAccountDetails;
}

public AccountDeepInfo getUserAccountDetails() {
	return userAccountDetails;
}

public void setUserAccountDetails(AccountDeepInfo userAccountDetails) {
	this.userAccountDetails = userAccountDetails;
}

 
}
