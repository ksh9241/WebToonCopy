package project.toy.webtoon_copy.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.toy.webtoon_copy.user.UserRequestDto;
import project.toy.webtoon_copy.webtoon.WebtoonRequestDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDto {

    Long commentSeq;
    String description;
    Long likeCount;
    Long notLikeCount;
    LocalDateTime createAt;
    UserRequestDto userRequestDto;
    WebtoonRequestDto webtoonDto;

    public Comment toEntity() {
        return Comment.builder()
                .commentSeq(commentSeq)
                .description(description)
                .likeCount(likeCount)
                .notLikeCount(notLikeCount)
                .user(userRequestDto.toEntity())
                .webtoon(webtoonDto.toEntity())
                .build();
    }
}
