package project.toy.webtoon_copy.cookiehst;

public enum PaymentCode {
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
