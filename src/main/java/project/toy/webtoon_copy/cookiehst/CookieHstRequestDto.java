package project.toy.webtoon_copy.cookiehst;

import lombok.*;
import project.toy.webtoon_copy.cookie.CookieRequestDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CookieHstRequestDto {
    Long cookieHstSeq;
    PaymentCode paymentStatusCd;
    Integer amount;
    Integer quantity;
//    LocalDateTime createDt = LocalDateTime.now();
//    LocalDateTime modifyDt;
//    LocalDateTime efctStDt = LocalDateTime.now();
//    LocalDateTime efctFnsDt = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
//    String tid; // 결제 고유 PK
//    String cid; // 가맹점 코드

//    @JsonBackReference
//    CookieRequestDto cookie;

    public CookieHst toEntity() {
        return CookieHst.builder()
                .cookieHstSeq(cookieHstSeq)
                .paymentStatusCd(paymentStatusCd)
                .amount(amount)
                .quantity(quantity)
                .build();
    }
}
