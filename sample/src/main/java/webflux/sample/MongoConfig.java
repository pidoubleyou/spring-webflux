package webflux.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import webflux.sample.repository.TicketEntity;

@Configuration
public class MongoConfig {
  @Bean
  public CommandLineRunner initData(MongoOperations mongo) {

    return (String... args) -> {
      TicketEntity[] tickets = new TicketEntity[]{
        TicketEntity.builder().id(0L).title("hallo").description("here").build()
      };

      mongo.dropCollection(TicketEntity.class);
      mongo.createCollection(TicketEntity.class);

      for (TicketEntity ticket : tickets) {
        mongo.save(ticket);
      }
    };
  }
}
