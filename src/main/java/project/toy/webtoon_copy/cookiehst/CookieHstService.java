package project.toy.webtoon_copy.cookiehst;

import java.util.List;

public interface CookieHstService {
    List<CookieHstRequestDto> findAllbyCookieSeq(Long cookieSeq);
    CookieHstRequestDto createCookieHst(CookieHstRequestDto cookieHstDto);

    CookieHstResponseDto cancelCookieHst(Long cookieHstSeq);

    CookieHstRequestDto cancelCookieHst(CookieHstRequestDto cookieHstDto);
}
