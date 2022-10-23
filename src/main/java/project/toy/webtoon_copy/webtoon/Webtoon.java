package project.toy.webtoon_copy.webtoon;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoon;
import project.toy.webtoon_copy.util.DayOfWeek;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Webtoon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "WEBTOON_SEQ")
    Long webtoonSeq;
    @NotNull
    String webtoonName;
    @NotNull
    String artist;
    @NotNull @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;
    @ColumnDefault("0")
    Float grade; // 평점
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;

    String fileName;
    String originFileName;

    @OneToMany(mappedBy = "webtoon")
    private List<Comment> comments = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "likeWebtoonSeq")
//    LikeWebtoon likeWebtoon;

}
