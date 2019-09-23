# MSA_ACCOUNT_API
MSA_USER is first project that msa project of all became basic. this project extend one service into Independent services


### mysql 도커 컨테이너 생성
docker run -d \
-p 2012:3306 \]
--name mysql-docker-container \
-e MYSQL_ROOT_PASSWORD=root123 \
-e MYSQL_DATABASE=msa_account \
-e MYSQL_USER=msa_admin \
-e MYSQL_PASSWORD=pass \
mysql:latest

### spring-boot
실행시 동적으로 msa_account 테이블이 생성된다
