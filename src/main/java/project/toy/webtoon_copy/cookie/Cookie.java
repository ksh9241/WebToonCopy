package project.toy.webtoon_copy.cookie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.cookiehst.CookieHst;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.util.Common;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cookie extends Common {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookieSeq;
    @Column(columnDefinition = "bigint(20) default '0'") // default 값 설정하는 거 처리해야 함.
    private Long cookieCount;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cookie")
    private List<CookieHst> cookieHst = new ArrayList<>();

    // 자체적인 오브젝트 내에서 처리하는 로직을 수행한다.
    public void useCookie(int count) {
        if (cookieCount < count) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
        cookieCount -= count;
    }

    public CookieResponseDto toDto() {
        return CookieResponseDto.builder()
                .cookieSeq(cookieSeq)
                .cookieCount(cookieCount)
                .userResponseDto(user.toDto())
                .build();
    }
}
