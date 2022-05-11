# product-api-server
상품 관련 API 서버


## 프로젝트 주요 관심사

- 클린 코드
- 객체지향적 설계
- 대규모 트래픽 처리



## 사용 기술

- Spring Boot
- Maven
- Mybatis
- MariaDB
- Java 11

## DB 관리
- DB 환경 구성을 위하여 docker를 사용
- DB 형상 관리를 위하여 flyway를 사용


## 실행 방법
1. Docker 실행 
```shell
docker-compose up -d
```



## 개발 히스토리

---
### API 응답 구조
[API 응답 구조 설계 이슈](https://github.com/suhani93/product-api/issues/1)

**정상 응답값**
- 응답값을 확장 가능한 구조로 설계
```json
{
	"code" : "",
	"data" : { }
}
```
`code` 응답 성공 결과에 대한 코드값 API 문서 참고  
`data` 요청에 대한 결과

**에러 응답값**
- 에러에 대해서 버그 리포트가 가능한 구조로 설계
```json
{
	"code" : "",
	"message" : "",
	"transactionId" : ""
}
```
`code` 는 해당 에러의 유형을 알려주는 코드 값으로 API 문서 참조  
`message` 는 해당 에러에 대한 메세지  
`transactionId` 는 해당 응답값이 실패한 이유를 추적하기 위한 각각의 요청마다 가지고 있는 UUID  

---

### API 공통 응답값

**성공 공통 응답값**

|상황|Status Code|Response Body `code`|
|---|:---:|:---:|
|정상 요청 성공|200|SUCCESS|

**에러 공통 응답값**

|상황|Status Code|Response Body `code`|Response Body `message`|
|---|:---:|:---:|---|
|Paramter 누락|400|MISSING_PARAMETER|'${paramterName}' is required |
|잘못된 Paramter 요청|400|INVALID_PARAMETER|'${paramterName}' ${validationErrorMessage} |
|리소스가 존재하지 않음 |404|NOT_FOUND|resource doesn't exist|
|서버 오류|500|INTERNAL_SERVER_ERROR|An unknown error has occurred. Please try again in a little while|


### DTO 관련 규칙

**네이밍 규칙**
- `client`에서 `controller`로 요청을 한 값을 담아주기 위한 객체 : XxxxRequest
- `controller`에서 `client`로 응답을 해주기 위한 객체 - XxxxResponse
- `Service` 내에서 로직을 위해 데이터를 전달해주는 객체 - XxxxDto
- `Repository` 에서 `service`로 데이터를 전달해주는 객체 - XxxxDto  

> ※ DTO에는 Getter 외의 어떠한 로직도 들어가지 않으며 불변객체로 만든다.  


### DB ERD




