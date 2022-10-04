package project.toy.webtoon_copy.cookie;

import project.toy.webtoon_copy.kakaopay.KakaoPayDto;

import java.util.List;

public interface CookieService {

    CookieDto createCookie(Long userSeq);

    String paymentToCookie(KakaoPayDto kakaoPayDto);

    CookieDto kakaoPaySuccess(String pg_token);
//    List<CookieDto> findPaymentHst(Long cookieSeq);
}
