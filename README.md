# SERVICE_POTAL_API
SERVICE_POTAL_API is first project that msa project of all became basic. this project extend one service into Independent services

## 연관 프로젝트
1) Front SERVER: SERVICE_PORTAL_PAGE<br>
https://github.com/dinosaurUncle/SERVICE_PORTAL_PAGE<br>
2) 프로젝트 관리: https://github.com/users/dinosaurUncle/projects/2

## 프로젝트 스펙 & 개발도구
JDK: JAVA 11<br>
Build Tool : Gradle<br>
Web framework: Spring Boot<br>
DB: Mysql<br>
IDE: IntelliJ IDEA 2018.2.8 (Ultimate Edition)<br> 

## local 개발환경 세팅
1) JDK 11 설치 및 환경설정 - 생략(구글링)<br>
2) Gradle 설치 및 환경설정 - 생략(구글링)<br>
3) 도커 설치 - https://www.docker.com/get-started <br>
4) mysql 도커 컨테이너 생성(DB용도)
<pre><code>
docker run -d \
-p 2012:3306 \
--name mysql-docker-container \
-e MYSQL_ROOT_PASSWORD=root123 \
-e MYSQL_DATABASE=msa_account \
-e MYSQL_USER=msa_admin \
-e MYSQL_PASSWORD=pass \
mysql:latest
</code></pre>
5) git 설치 - 생략(구글링)<br>
6) 프로젝트 내려받기<br>
<pre><code>
git clone https://github.com/dinosaurUncle/SERVICE_POTAL_API.git
</code></pre>
7) 프로젝트 Import(Intellij로 권장, 다른 IDE는 해보지 않음)<br>
Intellij에서 Import가 성공하면, Gradle Import버튼 한번 클릭<br>
8) 프로젝트 초기 데이터 세팅<br>
8-1) application.properties에 들어가서 spring.jpa.hibernate.ddl-auto 값을 validate -> create 로 변경하고 저장한다<br>
8-2) src/test/java/me.dinosauruncle.service.portal.domain.BasicDataSetting 실행<br>
8-3) application.properties에 들어가서 spring.jpa.hibernate.ddl-auto 값을 create -> validate 로 변경하고 저장한다<br>
9) 프로젝트 실행 - ServicePortalApplication을 실행한다

## swagger
http://localhost:8089/swagger-ui.html
 
