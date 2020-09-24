### Was ist reaktive Programmierung

Paradigma für 

<span class="color-highlight">non-blocking, asynchrone und Event getriebene</span> 

Anwendungen mit Fokus auf

<span class="color-highlight">Skalierbarkeit und Stabilität</span> 

und nicht Zeitverhalten

<-->

### Grundlegende Konzepte

* Asynchronität

* Reactive Streams

* Backpressure
  
* Immutability

* Funktionale Programmierung

<-->

### Wie werden Requests abgearbeitet

![image](./resources/request1.png)

<-->

### Wie werden Requests abgearbeitet

![image](./resources/request2.png)

<-->

### Wie werden Requests abgearbeitet

![image](./resources/request3.png)

<-->

### Wie werden Requests abgearbeitet

![image](./resources/request4.png)

<-->

### Einsatzszenarien

* Gateways

* Verarbeitung von sehr vielen Requests

* Service mit vielen externen Serviceaufrufen

* nebenläufige Messageverarbeitung

<--->

### Was bietet Spring

Bibliotheken auf Basis <span class="color-highlight">Project Reactor</span> 

* Spring Reactive Web 

* Spring Cloud Gateway

* Spring Cloud Function/Stream

<-->

### Spring Reactive Stack

![image](./resources/spring_stack.png)

<--->

### Mono und Flux

* <span class="color-important">Mono</span>: Stream, der nur <span class="color-highlight">ein</span> Ergebnis liefert
  * ein Wert + Complete
  * Fehler

* <span class="color-important">Flux</span>: Stream, der <span class="color-highlight">0-n</span> Ergebnisse liefert
  * 0-n Werte + Complete
  * 0-n Werte + Fehler 

<-->

### Codebeispiel

<pre><code data-trim data-noescape>
Mono.just(10L)
      .flatMap(ticketRepository::findById)
      .switchIfEmpty(Mono.error(NotFoundException::new))
      .filter(entry -> entry.getId() > 10)
      .map(entry -> entry.getId() + " " + entry.getTitle());

</code></pre>

<--->

### reaktive REST-API


* <span class="color-highlight">Annotation-based</span> reactive components

  * ähnlich Spring Web

* <span class="color-highlight">Functional</span> routing and handling

<-->

### Codebeispiel - Annotation Based

<pre><code data-trim data-noescape>
@PostMapping()
@ResponseStatus(HttpStatus.CREATED)
public Mono&lt;Ticket> createTicket(@RequestBody Mono&lt;Ticket> ticket) {
  return ticket
    .flatMap(this::setId)
    .map(Mapper::map)
    .flatMap(ticketRepository::save)
    .map(Mapper::map);
}

</code></pre>

<-->

### Codebeispiel - Functional routing and handling

#### Routing

<pre><code data-trim data-noescape>
@Bean
  public RouterFunction&lt;ServerResponse> route() {
    TicketHandler handler = new TicketHandler(ticketRepository);
    return RouterFunctions.route(POST("/v2/tickets")
        .and(contentType(APPLICATION_JSON)), handler::createTicket)
        .andRoute(GET("/v2/tickets")
        .and(accept(APPLICATION_STREAM_JSON)),
      handler::getTickets);
  }
</code></pre>

<-->

### Codebeispiel - Functional routing and handling

#### Handler

<pre><code data-trim data-noescape>
public Mono<ServerResponse> createTicket(ServerRequest request) {
    return request.bodyToMono(Ticket.class)
      .flatMap(this::setId)
      .map(Mapper::map)
      .flatMap(ticketRepository::save)
      .map(Mapper::map)
      .flatMap(
        ticket ->
          ServerResponse.created(
            UriComponentsBuilder
              .fromPath("ticket/" + ticket.getId())
              .build()
              .toUri())
              .contentType(APPLICATION_JSON)
              .body(fromValue(ticket)));
}
</code></pre>

<--->

### WebClient

* reaktive Client für http-Aufrufe

* kann auch in nicht reaktiven Spring-Umfeld eingesetzt werden

<-->

### WebClient - Konfiguration


<pre><code data-trim data-noescape>
@Bean
public WebClient webClient() {
  WebClient webClient = WebClient
      .builder()
      .defaultHeader("SOME_HEADER", "SOME_VALUE")
      .filter((clientRequest, exchangeFunction) -> {
        // do something...
      })
      .build();
}
</code></pre>

<-->

### WebClient - Request

<pre><code data-trim data-noescape>
 webClient.get()
        .uri(new URI("http://..."))
        .header("SOME_HEADER2", "value")
        .exchange()
        .map(response -> response.bodyToMono(String.class))
        .retry(2)
        .onErrorReturn(Mono.just("not found"));
</code></pre>

<--->

### Hinweise

Grundprinzipien verstehen!

Keine Blocking-Aufrufe in Code!

Debugging/Fehleranalyse lernen!

<p>
<span class="color-important">Verwendung gut überlegen!</span>
</p><!-- .element: class="fragment" -->
