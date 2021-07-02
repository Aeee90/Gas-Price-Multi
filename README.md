# Gas Price API

## Goal
이더리움 블록체인의 최신 개스 가격 정보를 수집, 가공해서 돌려주는 REST API 개발

1. 부트를 이용해 서버를 띄우고 /gasprice 를 GET 으로 호출하면 아래 모든 정보를 json
형식으로 돌려줍니다.
   
• 최신 블록의 Block Number (10 진수로)

• 블록의 트랜잭션 갯수

• 트랜잭션의 gas price 의 평균값 (Gwei 단위로 소숫점 이하 1 자리까지)

• 트랜잭션의 gas price 의 최대값 (Gwei 단위로 소숫점 이하 1 자리까지)

• 트랜잭션의 gas price 의 최소값 (Gwei 단위로 소숫점 이하 1 자리까지)

• 트랜잭션의 gas price 를 오름차순으로 정렬하고, 해당 gas price 의 트랜잭션 수를
함께 표시

o 예를 들어 gas price 가 1.5 가 1 개 1.8 이 2 개라면 prices: [{gasprice:1.5,
count:1}, {gasprice:1.8, count:2}, ... ] 이런식으로 gas price 로 그룹핑해서
가격순으로 정렬하고 같은 해당 가격의 트랜잭션 수를 넣습니다.

## Built With
(IDE에서는 Lombok PlugIn 필요합니다.)
- java 1.8
- Spring Boot 2.1.6.RELEASE
- Gradle
- Jackson(json Library)
- Lombok

## Authors
Aeee

