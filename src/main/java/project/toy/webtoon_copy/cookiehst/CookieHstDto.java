package project.toy.webtoon_copy.cookiehst;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import project.toy.webtoon_copy.cookie.CookieDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CookieHstDto {
    Long cookieHstSeq;
    PaymentCode paymentSttusCd;
    Integer amount;
    Integer quantity;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    LocalDateTime efctStDt = LocalDateTime.now();
    LocalDateTime efctFnsDt = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    String tid; // 결제 고유 PK
    String cid; // 가맹점 코드

//    @JsonBackReference
    CookieDto cookie;
}
