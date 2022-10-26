package project.toy.webtoon_copy.cookie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.cookiehst.CookieHst;
import project.toy.webtoon_copy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cookie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookieSeq;
    @Column(columnDefinition = "bigint(20) default '0'") // default 값 설정하는 거 처리해야 함.
    private Long cookieCount;
    @NotNull
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;

    @OneToOne(mappedBy = "cookie")
    private User user;

    @OneToMany(mappedBy = "cookie")
    private List<CookieHst> cookieHst = new ArrayList<>();
}
