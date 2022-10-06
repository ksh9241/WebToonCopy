package project.toy.webtoon_copy.likewebtoon;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
