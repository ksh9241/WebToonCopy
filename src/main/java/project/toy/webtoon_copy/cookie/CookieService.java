package project.toy.webtoon_copy.cookie;

import project.toy.webtoon_copy.kakaopay.KakaoPayRequestDto;
import project.toy.webtoon_copy.user.User;

public interface CookieService {

    CookieResponseDto createCookie(User user);

    String paymentToCookie(KakaoPayRequestDto kakaoPayRequestDto);

    void kakaoPaySuccess(String pg_token);

    void use(Long userSeq, int cookieValue);

    void cancelCookieHst(Long cookieHstSeq);

//    Cookie findByCookieSeq(Long cookieSeq);
//    List<CookieDto> findPaymentHst(Long cookieSeq);
}
