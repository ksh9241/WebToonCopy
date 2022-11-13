package project.toy.webtoon_copy.likewebtoon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@Transactional
public class LikeWebtoonServiceTest {

    @Autowired
    LikeWebtoonService likeWebtoonService;

    @Test
    @DisplayName("유저정보로 좋아요 웹툰 목록 가져오기")
    public void findAllByUserSeq() {
        List<LikeWebtoonResponseDto> list = likeWebtoonService.findAllLikeWebtoonByUserSeq(1L);
        assertThat(list.size(), is(2));
    }
}
