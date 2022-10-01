package project.toy.webtoon_copy.cookiehst;

import java.util.List;

public interface CookieHstService {
    List<CookieHstDto> findAllbyCookieSeq(Long cookieSeq);
    CookieHstDto createCookieHst(CookieHstDto cookieHstDto);
}
