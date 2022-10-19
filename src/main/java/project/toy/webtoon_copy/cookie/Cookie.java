package project.toy.webtoon_copy.cookie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.cookiehst.CookieHst;
import project.toy.webtoon_copy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cookie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookieSeq;
    @Column(columnDefinition = "varchar(20) default '0'") // default 값 설정하는 거 처리해야 함.
    private String cookieCount;
    @NotNull
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cookie")
    private List<CookieHst> cookieHst = new ArrayList<>();
}
