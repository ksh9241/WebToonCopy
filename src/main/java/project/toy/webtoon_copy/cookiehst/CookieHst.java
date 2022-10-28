package project.toy.webtoon_copy.cookiehst;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    String tid;
    String cid;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cookieSeq")
    private Cookie cookie;

}
