# Webflux Kata

## Szenario

Eine Bank benötigt ein Buchungssystem, dass eingehende Kontoumsätze auf den richtigen Konten verbucht.

## Architektur

Das System besteht aus drei Komponenten:
* customer: Service zur Verwaltung der Bankkunden und ihrer Konten
  * Port 8081
  * Embedded MongoDB als Datenhaltung
  * Initialisierung der MongoDB mit entsprechenden Kundendaten vorhanden
* transaction-generator: Generator für Kontoumsätze
  * Port 8082
* balance: Service zur Verbuchung der eingehenden Kontoumsätze
  * Port 8080
  * Embedded MongoDB als Datenhaltung

## Aufgaben

1. Implementiere den Service `customer` mit folgender REST-API:
* GET /customers: liefert alle Kunden inklusive aller ihrer Konten
* GET /customers?account=$accountNumber: liefert den Kunden, der das Konto mit der `accountNumber` besitzt, und nur die Information über das angefragte Konto.

2. Implementiere den Service `transaction-generator` mit folgender REST-API:
* GET /transactions: liefert periodisch neue Kontoumsätze (verwende einen Content-Type mit stream).

Ein Kontoumsatz besteht aus folgende Informationen:
* Nummer des Kontos, dem der Betrag belastet wird
* Nummer des Kontos, dem der Betrag gutgeschrieben wird
* Betrag

3. Implementiere den Service `balance` mit folgender REST-API:
* GET /balances: liefert für alle Kunden und alle ihre Konten den aktuellen Kontostand (verwende Content-Type mit stream)

Der Service verwendet die in 1 und 2 implementierten Services, um den Kontostand laufend aktuell zu halten. Liegt ein neuer Kontoumsatz vor, so wird das entsprechende Konto belastet bzw. der Betrag gut geschrieben.

4. Erweiterung um Bonitätsprüfung
* Erweitere den Service `customer` um ein Kreditlimit
* Erweitere den Service `balance` um eine Bonitätsprüfung vor der Buchung des Kontoumsatzes. Wenn das zu belastende Konto nicht gedeckt ist, wird die Buchung nicht durchgeführt.

5. Backpressure
* Der Service `balance` kann nur 10 Kontoumsätze pro Sekunde verarbeiten. Nutze das Konzept Backpressure, so dass der Generator nicht mehr Umsätze liefert. Dabei darf kein Umsatz verloren gehen.

6. RateLimiting
* Der Service `custoner` darf von einem Client nur 5 mal pro Sekunde aufgerufen werden. Baue ein entsprechendes RateLimiting in den Service ein.
* Der Service `balance` muss entsprechend darauf reagieren

7. Parallelisierung
Versuche durch Parallelisierung die Verarbeitung von so viel Kontoumsätzen wie nur möglich zu erreichen.
