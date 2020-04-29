package webflux.kata.customer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Account.AccountBuilder.class)
public class Account {
  private String number;
  private String description;

  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountBuilder {}
}
