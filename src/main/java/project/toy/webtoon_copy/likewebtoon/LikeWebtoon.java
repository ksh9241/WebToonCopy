package project.toy.webtoon_copy.likewebtoon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.webtoon.Webtoon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LikeWebtoon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long likeWebtoonSeq;
    @NotNull
    Long webtoonSeq;
    @NotNull
    Long userSeq;
    String likeYn;
    String notiYn;

    @OneToMany(mappedBy = "likeWebtoon")
    private List<Webtoon> webtoonList = new ArrayList<>();

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userSeq")
//    private User user;
}
