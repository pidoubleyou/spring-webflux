package webflux.kata.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class AccountEntity {
  @Id
  private Long id;
  private String number;
  private String description;
}
