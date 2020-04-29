package webflux.kata.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class CustomerEntity {
  @Id
  private Long id;
  private String name;
  private AccountEntity[] accounts;

}
