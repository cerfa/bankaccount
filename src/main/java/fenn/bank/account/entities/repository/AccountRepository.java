package fenn.bank.account.entities.repository;

import fenn.bank.account.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	List<Account> findByCustomerId(String customerId);
	Account findByAccountId(String accountId);
}