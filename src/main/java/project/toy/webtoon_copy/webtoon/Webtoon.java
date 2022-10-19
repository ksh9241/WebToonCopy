package project.toy.webtoon_copy.webtoon;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoon;
import project.toy.webtoon_copy.util.DayOfWeek;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Webtoon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long webtoonSeq;
    @NotNull
    String webtoonName;
    @NotNull
    String artist;
    @NotNull @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;
    @ColumnDefault("0")
    Integer grade;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;

    String fileName;
    String originFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likeWebtoonSeq")
    LikeWebtoon likeWebtoon;

}
