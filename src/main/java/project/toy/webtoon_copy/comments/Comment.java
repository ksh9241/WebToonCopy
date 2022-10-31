package project.toy.webtoon_copy.comments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import project.toy.webtoon_copy.cookiehst.CookieHst;
import project.toy.webtoon_copy.subcomments.SubComments;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.util.Common;
import project.toy.webtoon_copy.webtoon.Webtoon;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Comment extends Common {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentSeq;
    @NotNull
    String description;
    @ColumnDefault("0")
    Long likeCount;
    @ColumnDefault("0")
    Long notLikeCount;
    @ColumnDefault("'N'")
    String deleteYn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoonSeq")
    private Webtoon webtoon;

    @OneToMany(mappedBy = "comment")
    private List<SubComments> subComments = new ArrayList<>();

    public CommentResponseDto toDto() {
        return CommentResponseDto.builder()
                .commentSeq(commentSeq)
                .description(description)
                .likeCount(likeCount)
                .notLikeCount(notLikeCount)
                .userResponseDto(user.toDto())
                .webtoonResponseDto(webtoon.toDto())
                .build();
    }
}
