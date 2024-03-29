package fenn.bank.account.entities.repository.services;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        customerRepository.findAll().forEach(customerCollection::add);
        return customerCollection;
    }
    
    public AccountCreationResponse retrieveCreateCustomer(final UserData userData) throws AccountException{
    	AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		try {
			Optional<Customer> customer = customerRepository.findByNameAndSurname(userData.getName(), userData.getSurname());
			if (customer.isPresent()) {
				LOG.info("****** customer existing ******");
				accountCreationResponse.setResponseMessage("Accounting already existing");
				return accountCreationResponse;
			} else {
				Customer customerAccount = new Customer(CommonInfo.generateAccountId(),
						userData.getName(),
						userData.getSurname());
				save(customerAccount);
				LOG.info("****** create customer out ******");
				accountCreationResponse.setResponseMessage("You account  ID ::  ".concat(customerAccount.getCustomerId()));
				return accountCreationResponse;
			}
		}catch (Exception exception) {
			throw new AccountException(exception.getMessage());
		}

    }
    
    public UserAccTransactionDetailsResponse  retrieveUserAccountDetails(String userId) throws AccountException {
		UserAccTransactionDetailsResponse accDetailsResponse= new UserAccTransactionDetailsResponse();
		try {
		    Optional<Customer> customerAccount = customerRepository.findByCustomerId(userId);
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
			List<Account> accounts = accountService.retrieveAccounts(userId);

			if(!CollectionUtils.isEmpty(accounts)) {
				userAccountDetails.setAccountCollection(accounts);
			 }

			accDetailsResponse.setUserAccountDetails(userAccountDetails);
		   }

		}catch (Exception exception) {
			throw new AccountException(exception.getMessage());
		}

		return accDetailsResponse;
	}
    public String checkCustomerExistence(String customerId) {
    	Optional<Customer> customerAccount = customerRepository.findById(customerId);
    	return customerAccount.isPresent()? "OK":"KO";
    }
    
}