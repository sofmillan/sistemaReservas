# Booking System

## Endpoints

### Client related operations

#### POST: api/v1/client
To create a client on the database you must provide an id (number),  name (string), last name (string), address (string), age (string) and email (string).

**Request example:**
```json
{
  "clientId":123,
  "name":"Sofia",
  "lastName":"Millan",
  "address":"cll96",
  "age":"17",
  "email":"i@gmail.com"
}
```
**Expected response:**
```json
{
  "clientId":123,
  "name":"Sofia",
  "lastName":"Millan",
  "address":"cll96",
  "age":"17",
  "email":"i@gmail.com"
}
```

### Room related operations

#### POST: api/v1/room

To create a room on the database you must provide an room number (number),  room type (string, it could be premium or standard) and base price (number).

**Request example:**
```json
{
  "roomNumber":101,
  "type":"premium",
  "basePrice":100000.0
}
```
**Expected response:**
```json
{
  "roomNumber":101,
  "type":"premium",
  "basePrice":100000.0
}

```

### Booking related operations

#### POST: api/v1/client/{idClient}/room/{roomNumber}/date/{bookingDate}/book
To book a registered room just indicate a client's id, a room number and a date in the path.

**Request example:**
```
http://localhost:8080/api/v1/client/1/room/101/date/2023-05-05/book
```
**Expected response:**
```json
{
"bookingCode": "27d0a929-326f-4724-a334-97b3bcf73137",
	"bookingDate": "2023-05-05",
	"roomNumber": 101,
	"total": 95000.0
}
```

#### GET: api/v1/client/{idClient}
To get all the bookings from a client indicate the client's id in the path.
**Request example:**
```
http://localhost:8080/api/v1/client/1
```

**Expected response:**
```json
[
	{
	  "idClient": 1,
	  "clientFullName": "Sofia Millan",
	  "bookingCode": "27d0a929-326f-4724-a334-97b3bcf73137",
	  "bookingDate": "2023-05-05",
	  "roomNumber": 101,
	  "roomType": "premium",
	  "total": 95000.0
	},
  {
	  "idClient": 1,
		"clientFullName": "Sofia Millan",
		"bookingCode": "17a1a966-316b-4714-a144-97b3bcf33157",
		"bookingDate": "2023-06-05",
		"roomNumber": 102,
		"roomType": "premium",
		"total": 95000.0
	}
]
```
#### GET: api/v1/availableByDate?date={date}
To get all the available rooms for a specific date just indicate it in the path.

**Request example:**
```
http://localhost:8080/api/v1/availableByDate?date=2023-05-05
```
**Expected response:**
