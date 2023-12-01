package fenn.bank.account.entities.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fenn.bank.account.entities.Account;


 
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	Account findByCustomerId(String customerId);
}