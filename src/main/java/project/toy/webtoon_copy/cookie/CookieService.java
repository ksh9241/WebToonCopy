package project.toy.webtoon_copy.cookie;

import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.user.UserDto;

import java.util.List;

public interface CookieService {

    CookieDto createCookie(UserDto userDto);

    String paymentToCookie(KakaoPayDto kakaoPayDto);

    CookieDto kakaoPaySuccess(String pg_token);

//    Cookie findByCookieSeq(Long cookieSeq);
//    List<CookieDto> findPaymentHst(Long cookieSeq);
}
