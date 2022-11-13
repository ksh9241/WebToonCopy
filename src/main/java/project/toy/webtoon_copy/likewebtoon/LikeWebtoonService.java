package project.toy.webtoon_copy.likewebtoon;

import java.util.List;

public interface LikeWebtoonService {

    List<LikeWebtoonResponseDto> findAllLikeWebtoonByUserSeq(Long userSeq);

    void likeWebtoon(Long userSeq, Long webtoonSeq);
}
