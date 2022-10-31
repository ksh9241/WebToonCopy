package project.toy.webtoon_copy.comments;

import lombok.*;
import project.toy.webtoon_copy.user.UserRequestDto;
import project.toy.webtoon_copy.user.UserResponseDto;
import project.toy.webtoon_copy.webtoon.WebtoonRequestDto;
import project.toy.webtoon_copy.webtoon.WebtoonResponseDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    Long commentSeq;
    String description;
    Long likeCount;
    Long notLikeCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    UserResponseDto userResponseDto;
    WebtoonResponseDto webtoonResponseDto;


//    public Exception checkUser() {
//        if(CheckUtils.isEmpty(this.userDto.getUserSeq())) {
//            throw new UsernameNotFoundException("유저만 댓글을 입력할 수 있습니다.");
//        }
//        return null;
//    }
}
