package webflux.kata.customer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerEntity, Long> {
  Flux<CustomerEntity> findByAccountsNumber(String accountNumber);
}
