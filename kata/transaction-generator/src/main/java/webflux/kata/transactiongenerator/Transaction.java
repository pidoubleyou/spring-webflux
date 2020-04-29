package webflux.kata.transactiongenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Transaction.TransactionBuilder.class)
public class Transaction {
  private String payeeAccountNumber;
  private String payerAccountNumber;
  private Double amount;

  @JsonPOJOBuilder(withPrefix = "")
  public static class TransactionBuilder {}
}
