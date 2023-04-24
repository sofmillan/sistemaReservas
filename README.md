# Booking System
This service provides a hotel a structure that allows it to register clients and rooms on a database in order to book the rooms on a specific date. This project was made with:

- Java
- MySQL

And here are some of the tools that Spring Framework allow us to use:

- JUnit (for unit tests)
- Swagger (for API documentation)
- Spring Data (to store information in a database)
It also implements Git for version management and continuous integration and Railways for deployment.

Here's the link to see the api documentation: (documentation)[https://sistemareservas-production.up.railway.app/swagger-ui/index.html#]
## Diagrams

### Client diagram
<img src="https://user-images.githubusercontent.com/98916125/234051205-fe95d03c-9c92-4ba6-8387-902854c49cbb.jpg"  width="700" />

### Room diagram
<img src="https://user-images.githubusercontent.com/98916125/234054090-521149af-4b7b-46ab-af80-c32bf056c2dc.jpg"  width="700" />

### Booking diagram
<img src="https://user-images.githubusercontent.com/98916125/234066706-8548c085-9fca-4dc2-8919-ffb3937ec3ed.jpg"  width="700" />

### Exception diagram
<img src="https://user-images.githubusercontent.com/98916125/234104659-c3901c08-d9bb-4b6d-830c-a6e339fee9a4.jpg"  width="700" />

### Database diagram
<img src="https://user-images.githubusercontent.com/98916125/234094843-91753c6a-381e-4177-91e7-e0e26540dbc6.jpg"  width="700" />



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
To book a registered room just indicate a client's id, a room number and a date (yyyy-mm-dd) in the path.

**Request example:**
```
http://localhost:8080/api/v1/client/1/room/101/date/2023-05-05/book
```
**Expected response:**
```json
{
  "bookingCode":"27d0a929-326f-4724-a334-97b3bcf73137",
  "bookingDate": "2023-05-05",
  "roomNumber":"101",
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
    "bookingCode": "28d0b920-396d-1224-c334-17b3acf79168",
    "bookingDate": "2024-06-05",
    "roomNumber": 102,
    "roomType": "premium",
    "total": 190000.0
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
```json
[
  {
    "roomNumber":102,
    "type":"premium",
    "basePrice":250000.0
  },
  {
    "roomNumber":103,
    "type":"premium",
    "basePrice":300000.0
  },
  {
    "roomNumber":104,
    "type":"standard",
    "basePrice":60000.0
  }
]
```

#### GET: api/v1/availableByDateType?date={date}&type={type}
To get all the available rooms for a specific date and filter them by type just indicate both parameters in the path.

**Request example:**
```
http://localhost:8080/api/v1/availableByDate?date=2023-05-05&type=premium
```
**Expected response:**
```json
[
  {
    "roomNumber":102,
    "type":"premium",
    "basePrice":250000.0
  },
  {
    "roomNumber":103,
    "type":"premium",
    "basePrice":300000.0
  }
]
```
