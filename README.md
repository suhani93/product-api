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


## 개발 히스토리

---
### API 응답 구조
[API 응답 구조 설계 이슈](https://github.com/suhani93/product-api/issues/1)

**정상 응답값**
- 응답값을 확장 가능한 구조로 설계
```
{
	code : "",
	data : { }
}
```
`code` 응답 성공 결과에 대한 코드값 API 문서 참고
`data` 요청에 대한 결과

**에러 응답값**
- 에러에 대해서 버그 리포트가 가능한 구조로 설계
```
{
	code : "",
	message : "",
	transactionId : ""
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
|Paramter 누락|400|MISSING_PARAMETER|'{paramterName}' is required |
|Paramter 포맷 오류|400|INVALID_PARAMETER|'{paramterName}' is invalid parameter|
|Paramter 범위 오류|400|OUT_OF_RANGE|'{parameterName} is out of range \[ {min},{max} \]'|
|URI 존재하지 않음 |404|NOT_FOUND|'{requestUri}' is not found|
|서버 오류|500|INTERNAL_SERVER_ERROR|An unknown error has occurred. Please try again in a little while.|
