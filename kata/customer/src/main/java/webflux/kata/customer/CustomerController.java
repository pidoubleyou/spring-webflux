package webflux.kata.customer;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
public class CustomerController {

  private final CustomerRepository repository;

  @Autowired
  CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = "/customers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<Customer> get(@RequestParam Optional<String> accountNumber) {
    Flux<CustomerEntity> customers;

    if (accountNumber.isPresent()) {
      customers = repository.findByAccountsNumber(accountNumber.get());
    } else {
      customers = repository.findAll();
    }
    return customers.map(this::mapCustomer).log();
  }

  private Customer mapCustomer(CustomerEntity customerEntity) {
    return Customer.builder()
        .name(customerEntity.getName())
        .accounts(
            Arrays.stream(customerEntity.getAccounts())
                .map(this::mapAccount)
                .toArray(Account[]::new))
        .build();
  }

  private Account mapAccount(AccountEntity accountEntity) {
    return Account.builder()
        .number(accountEntity.getNumber())
        .description(accountEntity.getDescription())
        .build();
  }
}
