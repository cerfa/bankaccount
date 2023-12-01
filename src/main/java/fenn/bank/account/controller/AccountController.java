package fenn.bank.account.controller;

import java.net.URISyntaxException;

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

import com.sun.istack.NotNull;

import fenn.bank.account.dto.AccountCreationResponse;
import fenn.bank.account.dto.AccountInfo;
import fenn.bank.account.dto.UserAccTransactionDetailsResponse;
import fenn.bank.account.dto.UserData;
import fenn.bank.account.entities.repository.services.AccountService;
import fenn.bank.account.entities.repository.services.CustomerService;
import fenn.bank.account.exceptions.AccountException;


@RestController
@RequestMapping(value="/bankAccount")
public class AccountController extends GenericController{
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class.toString());

	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value="/addAccount",produces = "application/json")
	public ResponseEntity<AccountCreationResponse>retrieveAccountData(@RequestBody(required=true) @NotNull AccountInfo accountInfo) throws Exception{
		LOG.info("****** retrieveAccountData in ******");
		return ResponseEntity
				.ok()
				.body(accountService.retrieveCreateAccount(accountInfo));
	}
		
	@PostMapping(value="/createUser")
	public ResponseEntity<AccountCreationResponse> createUser(@RequestBody(required=true) @NotNull UserData userData) throws AccountException{
		LOG.info("****** create customer in ******");
		return ResponseEntity
				.ok()
				.body(customerService.retrieveCreateCustomer(userData));
	}
	
	@GetMapping(value="/checkUser/{userId}")
	public ResponseEntity<String> checkExitence(@PathVariable("userId") @NotNull String userId) throws AccountException{
		LOG.info("****** create customer in ******");
		return ResponseEntity
				.ok()
				.body(customerService.checkCustomerExistency(userId));
	}
	
	
	@GetMapping(value="/userAccount/details/{userId}")
	public ResponseEntity<UserAccTransactionDetailsResponse> retrieveAccountsDetails(@PathVariable("userId") @NotNull String userId) throws AccountException,URISyntaxException{
		LOG.info("****** create customer in ******");
		return ResponseEntity
				.ok()
				.body(customerService.retrieveUserAccountDetails(userId));
	}
	
}
