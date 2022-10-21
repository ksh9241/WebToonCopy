package project.toy.webtoon_copy.cookiehst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.toy.webtoon_copy.cookie.CookieDto;

import java.time.LocalDateTime;

@Data
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
    CookieDto cookie;
}
