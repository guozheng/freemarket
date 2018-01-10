Free Market Demo App
====================

# Design Overview

This is a quick demo app that implements a set of REST services for an online project labor market. Buyers submit projects looking for people to help. Sellers submit bids to get the project. Each project has a deadline, when deadline reaches, the lowest bid becomes the winning bid.

Tech stack used to implement the demo app:
   * [DropWizard](http://www.dropwizard.io/1.2.2/docs/): RESTful web service framework that has so many goodies, production ready out of box
   * [H2 Database Engine](http://www.h2database.com/html/main.html): a fast SQL DB that supports both in memory and traditional client server modes (we use client server mode here)
   * [JDBI v2](http://jdbi.org/jdbi2/archive.html): an easy wrapper for DB access
   * [Liquibase](http://www.liquibase.org): SQL DB schema management, integrates well witih DropWizard migration module for DB schema evolution
   
## Database Design

There are four tables/entities: seller, buyer, bid and project. The detailed definition can be found in `src/main/resources/migration.xml`, which is the liquibase schema definition.

## API Design

All the handlers can be found in `src/main/java/com/panda/freemarket/resources`. All the POST/PATCH body is in JSON, respnse is also in JSON. Here is a quick overview of the API:

   * POST `/api/v1/sellers?name={name}`: create a new seller, given its name, id is auto generated by DB
   * PATCH `/api/v1/sellers/{id}`: update an existing seller, e.g. update the name, etc.
   * GET `/api/v1/sellers/{id}`: get a seller by id
   * GET `/api/v1/sellers?from={from_id}&count={count}`: get a list of sellers by its id range, this provides paging support when there are too many sellers. Both `from` and `count` are optional, if they are missing, default values are: `from=1&count=10`
   * POST `/api/v1/buyers?name={name}`: create a new buyer, given its name, id is auto generated by DB
   * PATCH `/api/v1/buyers/{id}`: update an existing buyer, e.g. update the name, etc.
   * GET `/api/v1/buyers/{id}`: get a buyer by id
   * GET `/api/v1/buyers?from={from_id}&count={count}`: get a list of buyers by its id range, this provides paging support when there are too many buyers. Both `from` and `count` are optional, if they are missing, default values are: `from=1&count=10`
   * POST `/api/v1/projects`: create a new project, the POST body is the JSON for the project, see `model/Project.java` for details.
   * PATCH `/api/v1/projects/{id}`: update an existing project, e.g. update deadline, etc.
   * GET `/api/v1/projects/{id}`: get a project by id.
   * GET `/api/v1/projects?status={status}&from={from}&count={count}`: get a list of projects. `status` can be `open` (default if missing) or `closed`. `from` and `count` are the same as the above range get endpoints.
   * POST `/api/v1/projects/bids`: create a new bid, the POST body is the JSON for the bid, see `model/Bid.java` for details. If the project is closed already, new bid request will fail.
   * PATCH `/api/v1/projects/bids/{id}`: update an existing bid, e.g. change price, etc.
   * GET `/api/v1/projects/bids/{id}`: get a bid by id.
   * GET `/api/v1/projects/bids?seller_id={seller_id}&project_id={project_id}`: list bids either by given a `seller_id` or a `project_id`.

## TODOs

   * Create a DB trigger or background daemon to check and update project status based on `bid_deadline`
   * Add Unit Tests and Integration Tests
   * Enhance input data validation and sanatization
   * Add logs and metrics (DropWizard provides some metrics out of box already)
   * etc.

## Files and Locations

   * DropWizard config file: `app.yaml`
   * DB files: `/tmp/freemarket/*.db`
   * Application log files: application log is output to both console and `/tmp/freemarket/freemarket-application*.log` with gzip and rotation
   * Access log files: access log is output to both console and `/tmp/freemarket/freemarket-request*.log` with gzip and rotation
   * Liquibase schema: `migrations.xml`

# Usage

## Build
Clone the git repo, then build with Maven.
```text
mvn package
```

## Create/Update DB and Tables
If you run the app for the first time, or you made changes to liquibase schema `migrations.xml`, then you should run this:
```text
java -jar target/free-market-1.0-SNAPSHOT.jar db migrate app.yaml
```

## Create Fat Jar and Start Server
```text
mvn clean package
java -jar target/free-market-1.0-SNAPSHOT.jar server app.yaml
```

## Use the Services

Please follow the steps to populate the data in the right order.

### Sellers

Create new sellers
```text
curl -X POST \
  'http://localhost:8080/api/v1/sellers?name=guozheng' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
curl -X POST \
  'http://localhost:8080/api/v1/sellers?name=adam' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

Get sellers: by default from id 1 and count 10
```text
curl -X GET \
  http://localhost:8080/api/v1/sellers \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache'
```

Get sellers by given from and count values, in this example get seller id 2
```text
curl -X GET \
  'http://localhost:8080/api/v1/sellers?from=2&count=1' \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache'
```

Get seller by id
```text
curl -X GET \
  http://localhost:8080/api/v1/sellers/2 \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache'
```

### Buyers

Create new buyers
```text
curl -X POST \
  'http://localhost:8080/api/v1/buyers?name=google' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'

curl -X POST \
  'http://localhost:8080/api/v1/buyers?name=yahoo' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

Get buyers with default from id 1 and count 10
```text
curl -X GET \
  http://localhost:8080/api/v1/buyers \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache'
```

### Projects

Create new projects
```text
# note this first project bid_deadline is a past timestamp
curl -X POST \
  http://localhost:8080/api/v1/projects \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "my_chat",
	"description": "a cool new chat client",
	"bid_deadline": 1515577612,
	"buyer_id": 1
}'

curl -X POST \
  http://localhost:8080/api/v1/projects \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "my_fakebook",
	"description": "a cool facebook killer app",
	"bid_deadline": 2515577612000,
	"buyer_id": 1
}'

```

Show open projects, by default from id 1 with count of 10
```text
curl -X GET \
  http://localhost:8080/api/v1/projects \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

Show closed projects (deadline passed), by default from id 1 with count of 10
```text
curl -X GET \
  'http://localhost:8080/api/v1/projects?status=closed' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

Get project by id
```text
curl -X GET \
  http://localhost:8080/api/v1/projects/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

### Bids

Create new bids
```text
# send a bid to a project (id 1) that is already closed, should get error
curl -X POST \
  http://localhost:8080/api/v1/bids \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"project_id": 1,
	"seller_id": 1,
	"price": 10
}'

# send a bit to an open project (id 2)
curl -X POST \
  http://localhost:8080/api/v1/bids \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"project_id": 2,
	"seller_id": 1,
	"price": 10
}'
```

Get bid by id
```text
curl -X GET \
  http://localhost:8080/api/v1/bids/2 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

Get bid by project id (show all bids for it)
```text
curl -X GET \
  'http://localhost:8080/api/v1/bids?project_id=2' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```

Get bid by seller id (show all bids from the same seller)
```text
curl -X GET \
  'http://localhost:8080/api/v1/bids?seller_id=1' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```