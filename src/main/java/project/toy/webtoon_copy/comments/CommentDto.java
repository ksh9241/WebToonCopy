package project.toy.webtoon_copy.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.toy.webtoon_copy.util.CheckUtils;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements RootCommentDto{

    Long commentSeq;
    Long userSeq; // 1 : 1 관계 Entity로 변환하는게 나을 듯? Ui에서 보여줘야 할 정보는 유저아이디이기 때문에
    Long webtoonSeq;
    String description;
    Long likeCount;
    Long notLikeCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;

    @Autowired
    public Exception checkUser() {
        if(CheckUtils.isEmpty(this.userSeq)) {
            throw new UsernameNotFoundException("유저만 댓글을 입력할 수 있습니다.");
        }
        return null;
    }
}
