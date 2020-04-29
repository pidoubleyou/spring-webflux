package webflux.kata.transactiongenerator;

import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TransactionController {

  @GetMapping(value = "/transactions", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<Transaction> get() {
    return Flux.interval(Duration.ofMillis(200))
        // .onBackpressureDrop()
        .map(this::generateTransaction).log();
  }

  private Transaction generateTransaction(Long aLong) {
    int index1 = (int) (aLong % Accounts.ACCOUNT_NUMBERS.length);
    int index2 = (int) ((aLong + 3) % Accounts.ACCOUNT_NUMBERS.length);

    return Transaction.builder()
        .amount(10.0)
        .payeeAccountNumber(Accounts.ACCOUNT_NUMBERS[index1])
        .payerAccountNumber(Accounts.ACCOUNT_NUMBERS[index2])
        .build();
  }
}
