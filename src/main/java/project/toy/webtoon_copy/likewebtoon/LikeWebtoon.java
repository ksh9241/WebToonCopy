package project.toy.webtoon_copy.likewebtoon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.webtoon.Webtoon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeWebtoon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long likeWebtoonSeq;

    @ManyToOne
    Webtoon webtoon;

    @ManyToOne
    User user;

    public LikeWebtoonResponseDto toDto() {
        return LikeWebtoonResponseDto.builder()
                .likeWebtoonSeq(likeWebtoonSeq)
                .webtoonResponseDto(webtoon.toDto())
                .userResponseDto(user.toDto())
                .build();
    }


//    @OneToMany(mappedBy = "likeWebtoon")
//    private List<Webtoon> webtoonList = new ArrayList<>();

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userSeq")
//    private User user;
}
