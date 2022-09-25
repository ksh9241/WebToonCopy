package project.toy.webtoon_copy.subcomments;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class SubComments {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long subCommentSeq;

    @NotNull
    Long userSeq;
    @NotNull
    Long commentSeq;
    @NotNull
    String description;
    @ColumnDefault("0")
    Long likeCount;
    @ColumnDefault("0")
    Long notLikeCount;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;
}
