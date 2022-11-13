package project.toy.webtoon_copy.likewebtoon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.toy.webtoon_copy.webtoon.Webtoon;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeWebtoonRequestDto {

    Long likeWebtoonSeq;
    Webtoon webtoon;
    Long userSeq;
    String likeYn;
    String notiYn;
}
