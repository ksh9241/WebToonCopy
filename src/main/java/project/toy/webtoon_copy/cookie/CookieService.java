package project.toy.webtoon_copy.cookie;

import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.user.UserRequestDto;

import java.util.Map;

public interface CookieService {

    CookieRequestDto createCookie(UserRequestDto userDto);

    String paymentToCookie(KakaoPayDto kakaoPayDto);

    CookieRequestDto kakaoPaySuccess(String pg_token);

    Map<String, String> useCookie(Long userSeq, String cookieValue);

    String kakaoPayCancel(Long cookieHstSeq);

//    Cookie findByCookieSeq(Long cookieSeq);
//    List<CookieDto> findPaymentHst(Long cookieSeq);
}
