## Running the project
### Clone the project
```
git clone git@github.com:limoneren/deribit-integration.git
```
### Create the DB
- Create a docker volume to keep the mysql data persistent.
```
docker volume create mysql80-volume
```
- Run a container from the MySQL 8.0 image.
```
docker run --name mysql80 -p 3306:3306 -v mysql80-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:8.0
```
### Apply the Flyway Migrations
- Run flyway migrate command to create the schema.
```
 ./gradlew flywayMigrate 
```
### Run the Micronaut app
- Micronaut app will run in an embedded server.
```
 ./gradlew run
```
- (optional) Micronaut can automatically create Dockerfiles
```
 ./gradlew dockerfile
```

## cURL queries to check the service
- Getting the user's current balances and reserved funds for all available
  currencies
```
curl --location --request GET 'http://localhost:8080/deribit/account-summary?client-id=7H4NajEy' \
--header 'Content-Type: application/json' \
--header 'client-secret: JAZa95w_8DNw-mljpoTHUUUmj0_J4b1d-fSMdpyKr_Q'
```
- Getting history of withdrawals
```
curl --location --request GET 'http://localhost:8080/deribit/withdrawals?client-id=7H4NajEy&currency=BTC&count=10&offset=0' \
--header 'Content-Type: application/json' \
--header 'client-secret: JAZa95w_8DNw-mljpoTHUUUmj0_J4b1d-fSMdpyKr_Q'
```
- Getting history of deposits
```
curl --location --request GET 'http://localhost:8080/deribit/deposits?client-id=7H4NajEy&currency=BTC&count=10&offset=0' \
--header 'Content-Type: application/json' \
--header 'client-secret: JAZa95w_8DNw-mljpoTHUUUmj0_J4b1d-fSMdpyKr_Q'
```
- Withdraw from exchange to external crypto address. (Withdraw wallet needs 3 days to be approved.)
```
curl --location --request GET 'http://localhost:8080/deribit/withdraw?client-id=7H4NajEy&currency=BTC&address=bcrt1qev0hc6ejmct8zdw9ycs9dljjla2dlgchgjryrq&amount=0.001&priority=high' \
--header 'Content-Type: application/json' \
--header 'client-secret: JAZa95w_8DNw-mljpoTHUUUmj0_J4b1d-fSMdpyKr_Q'
```
