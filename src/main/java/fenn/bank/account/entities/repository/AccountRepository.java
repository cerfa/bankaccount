package fenn.bank.account.entities.repository;

import fenn.bank.account.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


 
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	Account findByCustomerId(String customerId);
}