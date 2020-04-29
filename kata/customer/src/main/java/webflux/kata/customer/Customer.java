package webflux.kata.customer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Customer.CustomerBuilder.class)
public class Customer {
  String name;
  Account[] accounts;

  @JsonPOJOBuilder(withPrefix = "")
  public static class CustomerBuilder {}
}
