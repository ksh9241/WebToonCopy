package project.toy.webtoon_copy.cookiehst;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CookieHst {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cookieHstSeq;
    @NotNull
    Long cookieSeq;
    @NotNull @Enumerated(EnumType.STRING)
    PaymentCode paymentSttusCd;
    Integer amount;
    Integer quantity;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;
    @NotNull
    LocalDateTime efctStDt;
    @NotNull
    LocalDateTime efctFnsDt;

}
