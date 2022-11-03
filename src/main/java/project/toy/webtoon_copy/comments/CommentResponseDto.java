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

}
