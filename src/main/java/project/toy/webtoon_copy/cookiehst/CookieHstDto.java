package project.toy.webtoon_copy.cookiehst;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookieHstDto {
    Long cookieHstSeq;
    Long cookieSeq;
    PaymentCode paymentSttusCd;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
}
