package webflux.kata.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class MongoConfig {
  @Bean
  public CommandLineRunner initData(MongoOperations mongo) {

    return (String... args) -> {
      CustomerEntity[] tickets = new CustomerEntity[]{
        CustomerEntity.builder()
          .id(1L).name("Hans Mustermann")
          .accounts(new AccountEntity[]{
            AccountEntity.builder().id(1L).number("DE1793863007365268").description("Girokonto").build(),
            AccountEntity.builder().id(2L).number("DE1793864007365268").description("Tagesfeld").build(),
            AccountEntity.builder().id(3L).number("DE1793865007365268").description("Festgeld").build()
          })
          .build(),
        CustomerEntity.builder()
          .id(2L).name("Angela Testperson")
          .accounts(new AccountEntity[]{
            AccountEntity.builder().id(4L).number("FR9830234379843378").description("Girokonto").build(),
            AccountEntity.builder().id(5L).number("LU3423846004786543").description("Tagesfeld").build()
          })
          .build(),
        CustomerEntity.builder()
          .id(3L).name("Julia Single-Test")
          .accounts(new AccountEntity[]{
            AccountEntity.builder().id(1L).number("DE9782034678912323").description("Girokonto").build()
          })
          .build(),
        CustomerEntity.builder()
          .id(4L).name("Mario Single-Test")
          .accounts(new AccountEntity[]{
            AccountEntity.builder().id(1L).number("DE9782034678912323").description("Girokonto").build()
          })
          .build(),
        CustomerEntity.builder()
          .id(5L).name("Jürgen Single-Test")
          .accounts(new AccountEntity[]{
            AccountEntity.builder().id(1L).number("DE9782034678919887").description("Girokonto").build()
          })
          .build(),
        CustomerEntity.builder()
          .id(6L).name("Sabine Meiermuster")
          .accounts(new AccountEntity[]{
            AccountEntity.builder().id(1L).number("DE89943523647388").description("Girokonto").build(),
            AccountEntity.builder().id(2L).number("DE23847543636273").description("Mietkonto").build()
          })
          .build(),
      };

      mongo.dropCollection(CustomerEntity.class);
      mongo.createCollection(CustomerEntity.class);

      for (CustomerEntity ticket : tickets) {
        mongo.save(ticket);
      }
    };
  }
}