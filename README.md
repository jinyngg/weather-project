
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

# 피드백 (23.06.05)

- 사용하지 않는 import문, 주석, 함수, 변수, 클래스등은 제거 ````완료````

![image](https://github.com/jinyngg/weather-project/assets/96164211/ae5ae9d0-a570-46ab-80a9-bf83235de862)

- DiaryService 클래스의 로그가 WeatherApplication 로 되어 있네요. 이부분 DiaryService 로 수정 ````완료````

![image](https://github.com/jinyngg/weather-project/assets/96164211/942710b1-c652-48c4-9173-d24de5579b23)

- GlobalExceptionHandler 에서 콘솔 출력을 로그로 변경 ````완료````

![image](https://github.com/jinyngg/weather-project/assets/96164211/33dbfcd5-fb96-4be1-8ba5-abb8b15cbf42)

- InvalidDate 가 예외처리 클래스이기 때문에, InvalidDateException로 변경 ````완료````

![image](https://github.com/jinyngg/weather-project/assets/96164211/3e74f3c8-5ec1-4e5b-a379-55590eea6d0d)

- DiaryService 클래스의 parseWeather 함수 구현에 있어서, get으로 특정 값을 가져오기 전에 키가 존재하는지를 체크 또는 가져온 값이 널이 아닌지를 체크하는 방어 로직 추가 ````완료````

![image](https://github.com/jinyngg/weather-project/assets/96164211/adc82a54-d6b4-48e2-a1fb-092a0db8c956)

- saveWeatherDate 함수에 트랙잭션, 외부 네트워크 연동하는 부분이나 스케쥴로 처리하는 부분을 트랙잭션으로 걸기 보다는 말그대로 전체를 처리하다가 문제가 되었을때 모두 취소할 수 있는 부분만 트랙잭션으로 처리 ````TODO````


- 예외처리 핸들러 구현에서 예외처리에 있어서, 그냥 Exception을 다시 발생시키기 보다는, 이곳에서 예외에 대한 처리가 마무리 되는 형태로 작성 ````TODO````

- openapi를 통해서 날씨를 가져오는 부분은 따로 서비스 클래스를 만들어서 처리 ````TODO````

- 서비스 부분은 인터페이스와 구현체를 따로 작성 ````TODO````

- 스케쥴 관련 처리에 있어도, 스케쥴이 호출되는 부분은 따로 클래스로 작성해 주시고, 이곳에서 관련 기능들에 대한 클래스나 함수를 호출해 주도록 처리 ````TODO````

````김조현```` 강사님 강의를 듣고 진행한 프로젝트입니다. 👨‍🎓
