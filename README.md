# MongoDB_Test Projekt

## Überblick

Das MongoDB_Test Projekt ist eine Java-basierte Anwendung, die eine REST-API zur Verwaltung von Inventargegenständen bereitstellt. Es nutzt Quarkus, das Supersonic Subatomic Java Framework, um eine effiziente und leichtgewichtige Anwendung zu erstellen. Die Anwendung integriert MongoDB Atlas als Datenbankdienst, um die Persistenz der Inventardaten in der Cloud zu gewährleisten.

## Funktionen

- **CRUD-Operationen**: Die API unterstützt das Erstellen, Lesen, Aktualisieren und Löschen von Inventargegenständen.
- **MongoDB Atlas Integration**: Nutzt MongoDB Atlas für die Speicherung und Abfrage von Inventardaten in der Cloud.

## API-Endpunkte

Die Anwendung definiert folgende REST-Endpunkte:

- `GET /items`: Listet alle Inventargegenstände auf.
- `POST /items`: Erstellt einen neuen Inventargegenstand.
- `GET /items/{id}`: Ruft einen spezifischen Inventargegenstand ab.
- `PUT /items/{id}`: Aktualisiert einen bestehenden Inventargegenstand.
- `DELETE /items/{id}`: Löscht einen spezifischen Inventargegenstand.

## MongoDB Atlas-Implementierung

Die Anwendung verwendet das `ItemRepo`-Repository, um mit der MongoDB Atlas-Datenbank zu interagieren. Die `InventoryItem`-Klasse definiert das Schema der Inventargegenstände, einschließlich Felder wie ID, Name und Menge. Die Konfiguration für die MongoDB Atlas-Verbindung wird in der `application.properties`-Datei festgelegt. Du musst deine Atlas-Verbindungszeichenfolge dort entsprechend konfigurieren.

## Voraussetzungen

- Java 11 oder höher
- Maven
- MongoDB Atlas-Konto und eine konfigurierte Datenbank

## Lokales Setup

1. Klone das Repository und navigiere in das Projektverzeichnis:
   ```
   git clone https://github.com/EpicSamuray/MongoDB_Test.git
   cd MongoDB_Test
   ```

2. Konfiguriere die MongoDB Atlas-Verbindungszeichenfolge in `src/main/resources/application.properties`. Du findest diese in deinem MongoDB Atlas-Dashboard unter dem Bereich "Connect your application".

3. Baue und starte die Anwendung:
   ```
   ./mvnw compile quarkus:dev
   ```

## Testen der API mit HTTPie

Installiere HTTPie, falls noch nicht geschehen: https://httpie.io/

Beispielbefehle zum Testen der API:

- **Liste aller Inventargegenstände**:
  ```
  http GET localhost:8080/items
  ```

- **Erstellen eines neuen Inventargegenstands**:
  ```
  http POST localhost:8080/items name='Neuer Gegenstand' quantity=10
  ```

- **Aktualisieren eines Inventargegenstands**:
  ```
  http PUT localhost:8080/items/{id} name='Aktualisierter Gegenstand' quantity=15
  ```

- **Löschen eines Inventargegenstands**:
  ```
  http DELETE localhost:8080/items/{id}
  ```

## Hinweise

Da MongoDB Atlas in der Cloud läuft, ist keine spezifische Docker-Konfiguration für die Datenbank erforderlich. Stelle sicher, dass deine Anwendung korrekt konfiguriert ist, um sich mit deiner MongoDB Atlas-Instanz zu verbinden.