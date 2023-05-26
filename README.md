
## Development Environment

- <img src="https://img.shields.io/badge/Windows-blue?style=flat&logo=windows&logoColor=white"/> 
- <img src="https://img.shields.io/badge/intellij-red?style=flat&logo=intellijidea&logoColor=white"/> 
- <img src="https://img.shields.io/badge/JDK_1.8-red?style=flat&logo=&logoColor=white"/>
- <img src="https://img.shields.io/badge/MySQL-blue?style=flat&logo=mysql&logoColor=white"/>
- <img src="https://img.shields.io/badge/Gradle-blue?style=flat&logo=gradle&logoColor=white"/>
- <img src="https://img.shields.io/badge/Github-grey?style=flat&logo=github&logoColor=white"/>

## Dependencies
- ````Spring Web````
- ````Srping JDBC````
- ````Spring Data JPA````
- ````MySql Database````
- ````Lombok````
- ````json-simple````

```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'com.googlecode.json-simple:json-simple:1.1.1'

	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'

	runtimeOnly 'mysql:mysql-connector-java:8.0.30'
}
```

## 1️⃣ 공통 요구사항

날씨, 일기를 작성/조회/수정/삭제 하는 백엔드를 구현

- 테스트 코드 작성
- 외부 API의 데이터를 활용 -> ````openweathermap API```` 
- JPA 방식으로 MySQL 사용

## 2️⃣ 최종 구현 API 리스트

✅ POST / create / diary
- date parameter (date 형식 : yyyy-MM-dd)
- text parameter 일기 글 작성
- 외부 API 에서 받아온 날씨 데이터와 함께 DB에 저장

✅ GET / read / diary
- date parameter 로 조회할 날짜 입력
- 해당 날짜의 일기를 List 형태로 반환

✅ GET / read / diaries
- startDate, ednDate parameter 로 조회할 날짜 기간의 시작일/종료일을 입력
- 해당 기간의 일기를 List 형태로 반환

✅ PUT / update / diary
- date parameter 로 수정할 날짜 입력
- text parameter 로 수정할 새 일기 글 입력
- 해당 날짜의 첫번째 일기 글을 새로 받아온 일기글로 수정

✅ DELETE / delete / diary
- date parameter 로 삭제할 날짜 입력
- 해당 날짜의 모든 일기 삭제

## 3️⃣ 프로젝트 완성도 높이기

- ✅ DB와 관련된 함수들을 트랜잭션 처리 ````완료````
- ✅ 매일 새벽 1시에 날씨 데이터를 외부 API 에서 받아다 DB에 저장해두는 로직을 구현 ````완료````
- ✅ logback 을 이용하여 프로젝트에 로그 이력 저장 ````완료````
- ✅ ExceptionHandler 을 이용한 예외처리 ````완료````
- ✅ Swagger 을 이용하여 API documentation ````완료````

````swagger````

![image](https://github.com/jinyngg/weather-project/assets/96164211/57c00697-2bea-46e7-8aa5-5f321d6b1555)

````Create Diary````

![image](https://github.com/jinyngg/weather-project/assets/96164211/ccc48cc2-a64e-4192-92d0-da9b9a8b2020)

````Read Diary````

![image](https://github.com/jinyngg/weather-project/assets/96164211/f8289bb6-616f-45d7-ae4c-6430733bd912)

````김조현```` 강사님 강의를 듣고 진행한 프로젝트입니다. 👨‍🎓
