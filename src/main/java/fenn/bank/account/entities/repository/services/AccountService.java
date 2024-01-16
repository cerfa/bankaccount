package fenn.bank.account.entities.repository.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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

	public Account retrieveAccountById(String accountId) {
		return accountRepository.findByAccountId(accountId);
	}
	public String retrieveAccountCheckBalance(String accountId, BigDecimal amount) {
		Account account = accountRepository.findByAccountId(accountId);
		if(null != account && account.getCredit().compareTo(amount) >= 0) {
			account.setCredit(account.getCredit().subtract(amount));
			accountRepository.save(account);
			return "OK";
		} else {
			return "KO";
		}
	}

	public AccountCreationResponse retrieveCreateAccount(final AccountInfo accountInfo) throws AccountException{
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();

		if(0 != accountInfo.getInitialCredit().compareTo(new BigDecimal("0"))
				&& customerService.checkCustomerExistence(accountInfo.getCustomerID()).equals("OK")) {
			LOG.info("****** createAccount out ******");
			Account customerAccount = new Account();
			customerAccount.setAccountId(CommonInfo.generateAccountId());
			customerAccount.setCredit(accountInfo.getInitialCredit());
			customerAccount.setCustomerId(accountInfo.getCustomerID());
			customerAccount.setTimeStamp(new Timestamp(System.currentTimeMillis()));
			accountCreationResponse.setResponseMessage("account added");
			save(customerAccount);
			return accountCreationResponse;
		}
		else {
			LOG.info("****** createAccount out ot credit ******");
			accountCreationResponse.setResponseMessage("NOT YET CUSTOMER. YOU SHOULD  CREATE CUSTOMER PROFILE");
			return accountCreationResponse;
		}
	}
	public List<Account> retrieveAccounts(String userId) {
		return accountRepository.findByCustomerId(userId);
	}
}