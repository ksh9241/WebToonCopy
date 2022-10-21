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
- KakaoPay.createCookieHst 이력 2개이상 쌓을 때부터 에러뜸 확인 필요 

#### 이슈 목록
- Gson 사용 시 LocalDateTime을 포맷설정 해줘야 한다.
- ModelMapper 변환 안되는 이슈
  - 생성자가 정의되지 않아서 발생했던 것으로 추정
    - @NoArgsConstructor
    - @AllArgsConstructor
    - @Data
- JPA 오랜만이라서 삽질 너무 많이한다..
  - nativeQuery : JPA 에서 JPQL 사용 시 SQL을 직접 사용하는 것