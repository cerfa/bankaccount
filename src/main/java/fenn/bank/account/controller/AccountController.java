package fenn.bank.account.controller;

import fenn.bank.account.dto.AccountCreationResponse;
import fenn.bank.account.dto.AccountInfo;
import fenn.bank.account.dto.UserAccTransactionDetailsResponse;
import fenn.bank.account.dto.UserData;
import fenn.bank.account.entities.Account;
import fenn.bank.account.entities.repository.services.AccountService;
import fenn.bank.account.entities.repository.services.CustomerService;
import fenn.bank.account.exceptions.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping(value="/bankAccount")
public class AccountController extends GenericController{
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class.toString());

	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private AccountService accountService;

	@PostMapping(value="/addAccount",produces = "application/json")
	public ResponseEntity<AccountCreationResponse>retrieveAccountData(@RequestBody AccountInfo accountInfo) throws AccountException {
		LOG.info("****** retrieveAccountData in ******");
		return ResponseEntity
				.ok()
				.body(accountService.retrieveCreateAccount(accountInfo));
	}
	@PostMapping(value="/createUser")
	public ResponseEntity<AccountCreationResponse> createUser(@RequestBody UserData userData) throws AccountException{
		LOG.info("****** create customer in ******");
		return ResponseEntity
				.ok()
				.body(customerService.retrieveCreateCustomer(userData));
	}
	@GetMapping(value="/checkUser/{userId}")
	public ResponseEntity<String> checkExistence(@PathVariable("userId") String userId) {
		LOG.info(" Check user already registered");
		return ResponseEntity
				.ok()
				.body(customerService.checkCustomerExistence(userId));
	}
	@GetMapping(value="/userAccount/details/{userId}")
	public ResponseEntity<UserAccTransactionDetailsResponse> retrieveAccountsDetails(@PathVariable("userId") String userId) throws AccountException {
		LOG.info("****** create customer details ******");
		return ResponseEntity
				.ok()
				.body(customerService.retrieveUserAccountDetails(userId));
	}
	@GetMapping(value="/userAccount/accounts/account/{accountId}")
	public ResponseEntity<Account> retrieveAccountsById(@PathVariable("accountId") String userId) {
		LOG.info("****** retrieve account by id ******");
		return ResponseEntity
				.ok()
				.body(accountService.retrieveAccountById(userId));
	}
	@GetMapping(value="/userAccount/accounts/account/check/{accountId}/{amount}")
	public ResponseEntity<String> retrieveAccountCheck(@PathVariable("accountId") String accountId,
														@PathVariable("amount") String amount) {
		LOG.info("****** check account balance ******");
		return ResponseEntity
				.ok()
				.body(accountService.retrieveAccountCheckBalance(accountId, new BigDecimal(amount)));
	}
}
