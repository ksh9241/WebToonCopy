package project.toy.webtoon_copy.cookiehst;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CookieHst {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cookieHstSeq;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cookieSeq")
    private Cookie cookie;

}
