package project.toy.webtoon_copy.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.util.CheckUtils;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto{

    Long commentSeq;
    Long webtoonSeq;
    String description;
    Long likeCount;
    Long notLikeCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    User user;

    public Exception checkUser() {
        if(CheckUtils.isEmpty(this.user.getUserSeq())) {
            throw new UsernameNotFoundException("유저만 댓글을 입력할 수 있습니다.");
        }
        return null;
    }
}
