package project.toy.webtoon_copy.comments;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentSeq;

    @NotNull
    Long userSeq; // DB는 유저 시퀀스만 가지고 있는게 맞는 것 같은데
    @NotNull
    Long webtoonSeq;
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
