package project.toy.webtoon_copy.webtoon;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoon;
import project.toy.webtoon_copy.util.DayOfWeek;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public WebtoonResponseDto toDto() {
        return WebtoonResponseDto.builder()
                .webtoonSeq(webtoonSeq)
                .webtoonName(webtoonName)
                .artist(artist)
                .dayOfWeek(dayOfWeek)
                .grade(grade)
                .fileName(fileName)
                .originFileName(originFileName)
                .build();
    }

}
