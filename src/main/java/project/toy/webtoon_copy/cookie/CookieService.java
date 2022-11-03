package project.toy.webtoon_copy.cookie;

import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserRequestDto;

import java.util.Map;

public interface CookieService {

    CookieResponseDto createCookie(User user);

    String paymentToCookie(KakaoPayDto kakaoPayDto);

    CookieRequestDto kakaoPaySuccess(String pg_token);

    void use(Long userSeq, int cookieValue);

    void cancelCookieHst(Long cookieHstSeq);

//    Cookie findByCookieSeq(Long cookieSeq);
//    List<CookieDto> findPaymentHst(Long cookieSeq);
}
