package project.toy.webtoon_copy.cookiehst;

import java.util.List;

public interface CookieHstService {
    List<CookieHstResponseDto> findAllbyCookieSeq(Long cookieSeq);

    CookieHstResponseDto createCookieHst(CookieHst cookieHst);

    CookieHst cancelCookieHst(Long cookieHstSeq);

}
