package project.toy.webtoon_copy.comments;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserDto;
import project.toy.webtoon_copy.util.CheckUtils;
import project.toy.webtoon_copy.webtoon.Webtoon;
import project.toy.webtoon_copy.webtoon.WebtoonDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto{ // Req, Res로 나눠서 관리하기. (변경에 취약해짐)

    Long commentSeq;
    String description;
    Long likeCount;
    Long notLikeCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    String deleteYn;
    UserDto userDto;
    WebtoonDto webtoonDto;

    public Exception checkUser() {
        if(CheckUtils.isEmpty(this.userDto.getUserSeq())) {
            throw new UsernameNotFoundException("유저만 댓글을 입력할 수 있습니다.");
        }
        return null;
    }
}
