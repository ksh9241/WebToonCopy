package project.toy.webtoon_copy.comments;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentSeq;

    @NotNull
    Long userSeq; // 1 : 1 관계 Entity로 변환하는게 나을 듯? Ui에서 보여줘야 할 정보는 유저아이디이기 때문에
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
