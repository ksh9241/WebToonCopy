package project.toy.webtoon_copy.cookiehst;

public enum PaymentCode {
    // 컬럼명 이해하기 쉽게 풀네임으로 변경
    PAYMENT("결제"),
    CANCEL("취소"),
    USE("사용");

    String description;

    PaymentCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
