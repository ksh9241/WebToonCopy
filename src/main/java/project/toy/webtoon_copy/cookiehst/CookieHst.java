package project.toy.webtoon_copy.cookiehst;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.util.Common;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CookieHst extends Common {


//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long cookieHstSeq;
    @NotNull @Enumerated(EnumType.STRING)
    PaymentCode paymentStatusCd; // CookieHstType
    Integer amount;
    Integer quantity;
    @NotNull

    String tid;
    String cid;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cookieSeq")
    private Cookie cookie;

}
