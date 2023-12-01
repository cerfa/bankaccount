package fenn.bank.account.entities.repository.services;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fenn.bank.account.dto.AccountCreationResponse;
import fenn.bank.account.dto.AccountDeepInfo;
import fenn.bank.account.dto.Transaction;
import fenn.bank.account.dto.UserAccTransactionDetailsResponse;
import fenn.bank.account.dto.UserData;
import fenn.bank.account.entities.Account;
import fenn.bank.account.entities.Customer;
import fenn.bank.account.entities.repository.CustomerRepository;
import fenn.bank.account.exceptions.AccountException;
import fenn.bank.account.external.services.TransactionConnector;

@Service
public class CustomerService {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class.toString());

    // @Autowired annotation provides the automatic dependency injection.
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionConnector trxConnector;
    
	@Autowired
	AccountService accountService;
    
    public Optional<Customer> retrieveCustomerById(String userId) {
    	return customerRepository.findByCustomerId(userId);
    }
    // Save Customer entity in the h2 database.
    public void save(final Customer customerAccount) {
        customerRepository.save(customerAccount);
    }
 
    // Get all Customer from the h2 database.
    public List<Customer> getAll() {
        final List<Customer> customerCollection = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> customerCollection.add(customer));
        return customerCollection;
    }
    
    public AccountCreationResponse retrieveCreateCustomer(final UserData userData) throws AccountException{
    	AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
    	Optional<Customer> customer = customerRepository.findByNameAndSurname(userData.getName(),userData.getSurname());
    	if(customer.isPresent()) {
    		LOG.info("****** create customer out ******");
    		accountCreationResponse.setResponseMessage("Accounting already existing");
    		return accountCreationResponse;
    	}
    	else {
    		 Customer customerAccount = new Customer(generateAccountId(),
    				  userData.getName(),
    				  userData.getSurname());
    		  save(customerAccount);
    		  LOG.info("****** create customer out ******");
    		  accountCreationResponse.setResponseMessage("You account  ID ::  ".concat(customerAccount.getCustomerId()));
    		return accountCreationResponse;
    	}
    }
    
    public UserAccTransactionDetailsResponse  retrieveUserAccountDetails(String userId) throws URISyntaxException{
		UserAccTransactionDetailsResponse accDetailsResponde= new UserAccTransactionDetailsResponse();
		Optional<Customer> customerAccount = customerRepository.findById(userId);
		if(customerAccount.isPresent()) {
			AccountDeepInfo userAccountDetails = new  AccountDeepInfo();
			userAccountDetails.setName(customerAccount.get().getName());
			userAccountDetails.setSurname(customerAccount.get().getSurname());
			userAccountDetails.setTransactionCollection(new ArrayList<>());
			final List<Transaction> userAccountTransactions = trxConnector.retrieveTrxList(userId);
			BigDecimal balance =  new BigDecimal("0");
			if(CollectionUtils.isEmpty(userAccountTransactions)) {
				userAccountDetails.setBalance(BigDecimal.ZERO);
				userAccountDetails.setTransactionCollection(new ArrayList<>()); 
			}
			else{
				Transaction accTransaction ;
				for(Transaction accItem : userAccountTransactions) {
					balance=balance.add(accItem.getAmount());
					accTransaction = new Transaction();
					BeanUtils.copyProperties(accItem, accTransaction);
					userAccountDetails.getTransactionCollection().add(accTransaction);
				} 
			}
			Account account= accountService.retrieveAccount(userId);
			userAccountDetails.setBalance(account.getCredit().subtract(balance));
			accDetailsResponde.setUserAccountDetails(userAccountDetails);
		}   	  
		return accDetailsResponde;
	}
    public String checkCustomerExistency(String customerId) throws AccountException{
    	Optional<Customer> customerAccount = customerRepository.findById(customerId);
    	return customerAccount.isPresent()? "OK":"KO";
    }
    private String generateAccountId() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().replace("-", "");
     return  "ACC".concat(uuidAsString);
    }
    
}