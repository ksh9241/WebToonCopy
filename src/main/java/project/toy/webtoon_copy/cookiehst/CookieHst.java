package project.toy.webtoon_copy.cookiehst;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CookieHst {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cookieHstSeq;
    @NotNull
    Long cookieSeq;
    @NotNull @Enumerated(EnumType.STRING)
    PaymentCode paymentSttusCd;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;

}
