package fenn.bank.account.entities.repository.services;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenn.bank.account.dto.AccountCreationResponse;
import fenn.bank.account.dto.AccountInfo;
import fenn.bank.account.entities.Account;
import fenn.bank.account.entities.repository.AccountRepository;
import fenn.bank.account.exceptions.AccountException;

@Service
public class AccountService {
	private static final Logger LOG = LoggerFactory.getLogger(AccountService.class.toString());

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerService customerService;

	// Save Account entity in the h2 database.
	public void save(final Account customerAccount) {
		accountRepository.save(customerAccount);
	}

	public AccountCreationResponse retrieveCreateAccount(final AccountInfo accountInfo) throws AccountException{
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();

		if(0 != accountInfo.getInitialCredit().compareTo(new BigDecimal("0"))
				&& customerService.checkCustomerExistency(accountInfo.getCustomerID()).equals("OK")
				&& !alreadyCreatedAccount(accountInfo.getCustomerID())) {
			LOG.info("****** createAccount out ******");
			Account customerAccount = new Account();
			customerAccount.setCredit(accountInfo.getInitialCredit());
			customerAccount.setCustomerId(accountInfo.getCustomerID());
			customerAccount.setTimeStamp(new Timestamp(System.currentTimeMillis()));
			accountCreationResponse.setResponseMessage("account added");
			save(customerAccount);
			return accountCreationResponse;
		}
		else {
			LOG.info("****** createAccount out ******");
			accountCreationResponse.setResponseMessage("");
			return accountCreationResponse;
		}
	} 
	
	private boolean alreadyCreatedAccount(String userId) {
		Account userAcc= accountRepository.findByCustomerId(userId);
		return userAcc != null;
	}
	
	public Account retrieveAccount(String userId) {
		return accountRepository.findByCustomerId(userId);
	}
}