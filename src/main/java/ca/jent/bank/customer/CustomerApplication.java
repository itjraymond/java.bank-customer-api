package ca.jent.bank.customer;

import ca.jent.bank.customer.data.Customer;
import ca.jent.bank.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}

@Component
@RequiredArgsConstructor
@Log4j2
class CustomerDataInitializer {
	private final CustomerRepository customerRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void ready() {
		Flux<Customer> customers = Flux.just(
				new Customer(null, "Bob", "Cruz", LocalDate.of(1970, Month.APRIL, 17)),
				new Customer(null, "Tim", "Bazz", LocalDate.of(1971, Month.DECEMBER, 30)),
				new Customer(null, "Santa", "Claus", LocalDate.of(1972, Month.DECEMBER, 25))
		).flatMap(customerRepository::save);

		customerRepository
				.deleteAll()
				.thenMany(customers)
				.thenMany(customerRepository.findAll())
				.subscribe(log::info);

	}

}
