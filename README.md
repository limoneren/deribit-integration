## Running the project
### Clone the project
```
git clone https://github.com/limoneren/deribit-integration.git
```
### Create the DB
- Create a docker volume to keep the mysql data persistent.
```
docker volume create mysql80-volume
```
- Run a container from the MySQL 8.0 image.
```
docker run --name mysql80 -p 3306:3306 -v mysql80-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:8.0
```
### Apply the Flyway Migrations
- Run flyway migrate command in the project directory to create the schema.
```
 ./gradlew flywayMigrate 
```
### Run the Micronaut app
- Micronaut app will run in an embedded server. Run the following command in the project directory.
```
 ./gradlew run
```
- (optional) Micronaut can automatically create Dockerfiles
```
 ./gradlew dockerfile
```

## cURL queries to check the service
- Getting the user's current balances and reserved funds for all available
  currencies. Note that initially, the database is populated with some test data coming from TestDataProvider class. This is for easier testing of db updates.
```
curl --location --request GET 'http://localhost:8080/deribit/account-summary?client-id=7H4NajEy' \
--header 'Content-Type: application/json' \
--header 'client-secret: JAZa95w_8DNw-mljpoTHUUUmj0_J4b1d-fSMdpyKr_Q'
```
- Getting history of withdrawals. There is no withdrawal history currently. Because user wallets require 3 days to be approved for withdrawals.
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
- Withdraw from exchange to external crypto address. Withdraw wallet needs 3 days to be approved. That's why, this request is returning with "address is not usable yet" error.
```
curl --location --request GET 'http://localhost:8080/deribit/withdraw?client-id=7H4NajEy&currency=BTC&address=bcrt1qev0hc6ejmct8zdw9ycs9dljjla2dlgchgjryrq&amount=0.001&priority=high' \
--header 'Content-Type: application/json' \
--header 'client-secret: JAZa95w_8DNw-mljpoTHUUUmj0_J4b1d-fSMdpyKr_Q'
```
