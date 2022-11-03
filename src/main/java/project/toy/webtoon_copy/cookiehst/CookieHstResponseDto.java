package project.toy.webtoon_copy.cookiehst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CookieHstResponseDto {

    Long cookieHstSeq;
    PaymentCode paymentStatusCd;
    Integer amount;
    Integer quantity;
    String tid; // 결제 고유 PK
    String cid; // 가맹점 코드
}
