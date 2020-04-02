package webflux.sample.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TicketRepository extends ReactiveMongoRepository<TicketEntity, Long> {
}
