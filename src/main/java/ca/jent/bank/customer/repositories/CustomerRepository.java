package ca.jent.bank.customer.repositories;

import ca.jent.bank.customer.data.Customer;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@EnableReactiveMongoRepositories
public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {}
