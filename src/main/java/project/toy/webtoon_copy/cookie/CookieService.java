package project.toy.webtoon_copy.cookie;

import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.user.UserDto;

import java.util.List;
import java.util.Map;

public interface CookieService {

    CookieDto createCookie(UserDto userDto);

    String paymentToCookie(KakaoPayDto kakaoPayDto);

    CookieDto kakaoPaySuccess(String pg_token);

    Map<String, String> useCookie(Long userSeq, String cookieValue);

//    Cookie findByCookieSeq(Long cookieSeq);
//    List<CookieDto> findPaymentHst(Long cookieSeq);
}
