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

zwei Bibliotheken auf Basis <span class="color-highlight">Project Reactor</span> 

* Spring Webflux 

* Spring Cloud Gateway

<-->

### Spring Reactive Stack

![image](./resources/spring_stack.png)

<--->

### Was sind Mono und Flux

* <span class="color-important">Mono</span>: Stream, der nur ein Ergebnis liefert
  * ein Wert + Complete
  * Fehler

* <span class="color-important">Flux</span>: Stream, der 0-n Ergebnisse liefert
  * 0-n Werte + Complete
  * 0-n Werte + Fehler 

<--->

### Codebeispiel

<--->

### Hinweise

Grundprinzipien verstehen!

Keine Blocking-Aufrufe in Code!

Debugging/Fehleranalyse lernen!

<p>
<span class="color-important">Verwendung gut überlegen!</span>
</p><!-- .element: class="fragment" -->
