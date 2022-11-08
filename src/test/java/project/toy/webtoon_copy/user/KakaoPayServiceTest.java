package project.toy.webtoon_copy.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.toy.webtoon_copy.kakaopay.KakaoPay;
import project.toy.webtoon_copy.kakaopay.KakaoPayRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KakaoPayServiceTest {

    @Autowired
    KakaoPay kakaoPay;

    @Test
    public void ready() {
        String result = kakaoPay.kakaoPayReady(new KakaoPayRequestDto());
        assertThat(result).isEqualTo("/pay");
    }
}
