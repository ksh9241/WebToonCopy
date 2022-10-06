package project.toy.webtoon_copy.likewebtoon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.toy.webtoon_copy.webtoon.Webtoon;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeWebtoonDto {

    Long likeWebtoonSeq;
    Webtoon webtoon;    // 이거 연관관계가 1 : 1인데 생각좀 해보자.
    Long userSeq;
    String likeYn;
    String notiYn;
}
