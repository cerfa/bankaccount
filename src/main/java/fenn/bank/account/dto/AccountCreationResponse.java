package fenn.bank.account.dto;

public class AccountCreationResponse {
 private String responseMessage;

public AccountCreationResponse() {
	//Just for the sake of making sonar happy.
}

public AccountCreationResponse(String responseMessage) {
	this.responseMessage = responseMessage;
}

public String getResponseMessage() {
	return responseMessage;
}

public void setResponseMessage(String responseMessage) {
	this.responseMessage = responseMessage;
} 
 
}
