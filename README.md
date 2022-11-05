# 웹툰 카피 서비스
네이버 웹툰 카피 API

## 요구사항
- 각 요일 별 웹툰이 조회되어야 한다.
  - 웹툰 별 평점, 조회수로 정렬이 되어야 한다.
  - 웹툰은 인증받지 않은 고객도 사용할 수 있어야한다.
  - 댓글, 평점. 미리보기 등의 서비스 이용 시 인증여부 확인 후 허가되지 않았을 경우 로그인 페이지로 이동한다.
- 댓글
  - 댓글은 kafka를 이용한 Message Queue 방식으로 처리가 진행되어야 한다.
- 쿠키
  - 쿠키는 결제 API를 통해 이루어져야 한다.
  - 결제 취소 API를 통해 결제취소가 이루어져야 한다.
  - 쿠키 자동결제 여부, 자동결제 갯수 컬럼 추가 및 Spring Batch 구현 (할일)
- 관리자
  - 관리자는 웹툰의 등록, 수정, 삭제가 가능해야 한다.

## 구현 객체
- Customer
  - 각 고객 별 쿠키 관리
  - 관심있는 웹툰 목록 관리
  - 내 댓글 작성 목록 관리

- Webtoon
  - 요일 별 웹툰
  - 각 웹툰 별 댓글 목록
  - 평점

- Comment
  - 작성자
  - 댓글내용
  - 웹툰 pk
  - 대댓글

- SubComment

- Cookie

- LikeWebtoon
  - 사용자 정보를 받아서 조회


### 해야할 일
- 근본적으로 JPA 디폴트 값이 0으로 들어가게 수정해야됨.
- 댓글, 대댓글 기능 kafka 이용해서 MQ방식으로 처리하기
- SpringBatch로 쿠키 자동결제 여부 확인 후 개수 체크 이후 자동결제 처리?
- 리팩토링 카카오페이 테스트 및 댓글삭제 오류잡기


#### 이슈 목록
- Gson 사용 시 LocalDateTime을 포맷설정 해줘야 한다.
- ModelMapper 변환 안되는 이슈
  - 생성자가 정의되지 않아서 발생했던 것으로 추정
    - @NoArgsConstructor
    - @AllArgsConstructor
    - @Data
- JPA 오랜만이라서 삽질 너무 많이한다..
  - nativeQuery : JPA 에서 JPQL 사용 시 SQL을 직접 사용하는 것
  - JPA StackOverflowError 이슈 발생
    - @Data를 사용 시 JPA 조인 관계에서 toString을 사용하는데 양방향 관계로 인해 무한루프되는 문제가 발생 
  - LazyInitializationException : Fetch.Lazy일 때 이슈가 발생하며 @Transactional을 추가한다.
    - Fetch Join으로 JPQL 을 사용한다.
  - ManyToOne 을 한다고 해서 OneToMany해야하는 것은 아니다.

- ModelMapper
  - A Dto에 B 오브젝트 필드, B Dto에 A 오브젝트 필드 값이 존재 시 무한루프 됨.
    - 해결방법으로 @JsonIgnore로 직렬화, 역직렬화 속성 무시 (DTO 필드에도 @JsonIgnore 사용가능)
    - @ToString 사용 지양


- 단축키
  - 커맨드 + 쉬프트 + T -> 관련된 객체 찾기

- 피드백 받고 수정할 부분
  - DTO 하나로 쓰지말고 Req, Res로 두개로 사용하기.
  - 서비스 @트랜잭션 어노테이션 추가
  - RestFul URL 규칙
  - Repository 타입 Optional<?> 로 변경
  - 패키지 명명규칙 (하이픈 이딴거 x)
  - validation Spring에서 제공해주는걸로 교체
  - DTO는 Controller에서 받아서 엔티티로 변환 후 서비스에 던지기.
  - Tdd에서 트랜잭션 옵션 넣어줘야 함.
  - 생성자 주입에 대한 부분 복습
  - kakaoPay Object 전체적으로 CookieHst에 대한 부분 빼라 (CookieHstDto 서비스에서 처리할 수 있는 것들은 처리하고 kakaoPay호출 [어차피 에러 시 트랜잭션 롤백 됨.])
  - enum 컬럼명 이해하기 쉽게 풀네임으로 변경
  - 공통 부분 따로 엔티티 만들어서 상속받기
    - createDt, modifyDt, efctStDt, efctFnsDt 등등
  - 테스트코드 작성하기
 ```java
kakaoPay Object
  // 싱글톤이라 이전 결과에 대한 데이터가 남아있어서 문제됨. (전역변수 x)
    private KakaoPayVo kakaoPayVo;
    private KakaoPayApprovalVo kakaoPayApprovalVo;
    private KakaoPayDto kakaoPayDto;

// 이 부분 cookieHst 처리 후 호출해서 카카오 페이 결제 취소만 하면 되는데 역할에 대한 침범이니까 이 부분 교체
public String kakaoPayCancel(CookieHstDto cookieHstDto) { // Cid, Tid, Amount
        RestTemplate restTemplate = new RestTemplate();

        cookieHstDto = findCookieInCookieDto(cookieHstDto, cookieHstDto.getCookie().getCookieSeq());
        if (cancelCookieCountCheck(cookieHstDto)) { // 이 부분도 CookieHst 도메인에서 처리
        return "쿠키 개수가 부족합니다.";
        }

if ("CANCEL_PAYMENT".equals(kakaoPayVo.getStatus())) {
// 이 부분도 빼고 에러 시 예외반환 해서 트랜잭션 롤백처리만
//                cancelCookieHst(cookieHstDto);  // 충전 이력 만료
//                createCookieHst(cookieHstDto);  // 취소 이력 생성
//                updateCancelCookie(cookieHstDto);   // 쿠키 개수 수정
        }

Cookie
// 자체적인 오브젝트 내에서 처리하는 로직을 수행한다. (책임에 대한 부분)
public void useCookie(int count) {
        if (cookieCount < count) {
        throw new IllegalStateException();
        }

        cookieCount -= count;
        }

```