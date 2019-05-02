## ATM_Project

Backend REST ATM application simulating simple Bank using Java Spring Boot with JWT authentication and H2 in memory database

## 1. Prerequisites

**JDK11**

**Maven** (not needed, u can use wrapper ```./mvnw``` instead of ```mvn```)

## Installation


---

1. clone this source

2. build
```shell
mvn clean package
```

3. run
```shell
mvn spring-boot:run
```

---
## Informations

Application by default is working at port ```8080```, u can change it by adding ```server.port``` value in ```application.properties```

Working Server example is available at [https://tklimczakatm.azurewebsites.net/](https://tklimczakatm.azurewebsites.net/)

Default data in database are 2 accounts (_locally you can change them in ```data.sql``` file in resources_):
```json
{
    "cardNumber": "0000000000000000",
    "balance": "500000",
    "pin": "0000"
}
```
```json
{
    "cardNumber": "1111111111111111",
    "balance": "99999",
    "pin": "1234"
}
```

## REST Methods

### 1. Authorization

#### POST /atm/auth

##### _Example_
```POST method``` on [https://tklimczakatm.azurewebsites.net/atm/auth](https://tklimczakatm.azurewebsites.net/atm/auth)

**body**
```json
{
    "cardNumber": "0000000000000000",
    "pin": "0000"
}
```

**response**

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMDAwMDAwMDAwIiwiaWF0IjoxN...",
    "tokenType": "Bearer",
    "number": "0000000000000000"
}
```
---

### 2. Balance

#### GET /atm/balance
##### _Request Headers_
  ```Authorization: Bearer _token_u_get_with_authorization_request```

##### _Example_
```GET method``` on [https://tklimczakatm.azurewebsites.net/atm/balance](https://tklimczakatm.azurewebsites.net/atm/balance)

**response**

```json
500000
```
---

### 3. Withdraw money

#### GET /atm/withdraw/500
##### _Request Headers_
  ```Authorization: Bearer _token_u_get_with_authorization_request```

##### _Example_
```GET method``` on [https://tklimczakatm.azurewebsites.net/atm/withdraw/500](https://tklimczakatm.azurewebsites.net/atm/withdraw/500)

**response**

_just check status if its ```200``` everything went OK, if u exceeded the balance u will get this error_
```json
{
    "timestamp": "2019-05-02T00:07:37.778+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Not enough balance on your account!",
    "path": "/atm/withdraw/5102312313131"
}
```
---

### 4. Deposit money

#### GET /atm/add/500
##### _Request Headers_
  ```Authorization: Bearer _token_u_get_with_authorization_request```

##### _Example_
```GET method``` on [https://tklimczakatm.azurewebsites.net/atm/add/500](https://tklimczakatm.azurewebsites.net/atm/add/500)

**response**

_just check status if its ```200``` everything went OK_

---
