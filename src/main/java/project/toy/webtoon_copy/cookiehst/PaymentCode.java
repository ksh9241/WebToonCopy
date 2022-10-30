package project.toy.webtoon_copy.cookiehst;

public enum PaymentCode {
    // 컬럼명 이해하기 쉽게 풀네임으로 변경
    A("결제"),
    C("취소"),
    S("사용");

    String description;

    PaymentCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
