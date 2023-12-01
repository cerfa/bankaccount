package fenn.bank.account.entities.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fenn.bank.account.entities.Customer;


 
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
	Optional<Customer> findByNameAndSurname(String name, String surname);
	Optional<Customer> findByCustomerId(String userId);
}