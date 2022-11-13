package project.toy.webtoon_copy.likewebtoon;

import lombok.*;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserResponseDto;
import project.toy.webtoon_copy.webtoon.Webtoon;
import project.toy.webtoon_copy.webtoon.WebtoonResponseDto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LikeWebtoonResponseDto {

    Long likeWebtoonSeq;
    WebtoonResponseDto webtoonResponseDto;
    UserResponseDto userResponseDto;
}
