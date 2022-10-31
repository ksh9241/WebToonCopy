package project.toy.webtoon_copy.cookiehst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CookieHstResponseDto {

    Long cookieHstSeq;
    PaymentCode paymentStatusCd;
    Integer amount;
    Integer quantity;
}
