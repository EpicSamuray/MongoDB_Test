# MongoDB_Test Projekt

## Überblick

Das MongoDB_Test Projekt ist eine Java-basierte Anwendung, die eine REST-API zur Verwaltung von Inventargegenständen bereitstellt. Es nutzt Quarkus, das Supersonic Subatomic Java Framework, um eine effiziente und leichtgewichtige Anwendung zu erstellen. Die Anwendung integriert MongoDB Atlas als Datenbankdienst, um die Persistenz der Inventardaten in der Cloud zu gewährleisten.

## Funktionen

- **CRUD-Operationen**: Die API unterstützt das Erstellen, Lesen, Aktualisieren und Löschen von Inventargegenständen.
- **MongoDB Atlas Integration**: Nutzt MongoDB Atlas für die Speicherung und Abfrage von Inventardaten in der Cloud.

## API-Endpunkte und Responses

Die `InventoryItem`-Objekte haben die folgenden Attribute: `name`, `price`, `quantity`, und `location`. Die `id` wird automatisch von MongoDB als `ObjectId` generiert.

### `GET /items`
- **Beschreibung**: Listet alle Inventargegenstände auf.
- **Response**: Eine Liste von Inventargegenständen. Jeder Gegenstand wird als JSON-Objekt dargestellt, das Felder wie `id`, `name`, `price`, `quantity`, und `location` enthält.

### `POST /items`
- **Beschreibung**: Erstellt einen neuen Inventargegenstand.
- **Body**: `{ "name": "Neuer Gegenstand", "price": 20.5, "quantity": 10, "location": "Lager A" }`
- **Response**: Das erstellte Inventarobjekt, einschließlich seiner `id`.

### `GET /items/{id}`
- **Beschreibung**: Ruft einen spezifischen Inventargegenstand ab.
- **Response**: Das Inventarobjekt, das der angegebenen `id` entspricht.

### `PUT /items/{id}`
- **Beschreibung**: Aktualisiert einen bestehenden Inventargegenstand.
- **Body**: `{ "name": "Aktualisierter Gegenstand", "price": 25.0, "quantity": 15, "location": "Lager B" }`
- **Response**: Das aktualisierte Inventarobjekt.

### `DELETE /items/{id}`
- **Beschreibung**: Löscht einen spezifischen Inventargegenstand.
- **Response**: Eine Bestätigung, dass der Gegenstand gelöscht wurde.

## Verbindung zu MongoDB Atlas

Die Anwendung verbindet sich mit MongoDB Atlas über die Konfigurationseinstellungen in der `application.properties`-Datei. Um die Verbindung zu MongoDB Atlas herzustellen, musst du die Verbindungszeichenfolge (URI) in der `application.properties`-Datei deiner Anwendung konfigurieren:

```
quarkus.mongodb.connection-string=mongodb+srv://<username>:<password>@<cluster-address>/<database>?retryWrites=true&w=majority
```

Ersetze `<username>`, `<password>`, `<cluster-address>` und `<database>` durch deine tatsächlichen MongoDB Atlas-Anmeldedaten und Datenbankinformationen.

## Optionales lokales Setup

Standardmäßig ist die Anwendung so konfiguriert, dass sie mit einer MongoDB Atlas-Instanz verbunden ist. Wenn du jedoch ein eigenes lokales Setup aufbauen möchtest, folge den Anweisungen im Abschnitt "Verbindung zu MongoDB Atlas".

## Testen der API mit HTTPie

Installiere HTTPie, falls noch nicht geschehen: https://httpie.io/

Beim Testen der API mit HTTPie musst du den `Content-Type`-Header auf `application/json` setzen. Hier sind aktualisierte Beispiele, wie du die verschiedenen Endpunkte der API aufrufen kannst:

### Liste aller Inventargegenstände

```sh
http GET localhost:8080/items
```

### Erstellen eines neuen Inventargegenstands

```sh
http POST localhost:8080/items \
Content-Type:application/json \
name='Neuer Gegenstand' \
price:=20.5 \
quantity:=10 \
location='Lager A'
```

### Ruft einen spezifischen Inventargegenstand ab

```sh
http GET localhost:8080/items/{id}
```

### Aktualisiert einen bestehenden Inventargegenstand

```sh
http PUT localhost:8080/items/{id} \
Content-Type:application/json \
name='Aktualisierter Gegenstand' \
price:=25.0 \
quantity:=15 \
location='Lager B'
```

### Löscht einen spezifischen Inventargegenstand

```sh
http DELETE localhost:8080/items/{id}
```