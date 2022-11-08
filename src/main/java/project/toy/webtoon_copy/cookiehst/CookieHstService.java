package project.toy.webtoon_copy.cookiehst;

import java.util.List;

public interface CookieHstService {
    List<CookieHstResponseDto> findAllbyCookieSeq(Long cookieSeq);

    CookieHst cancelCookieHst(Long cookieHstSeq);

    CookieHstResponseDto createCookieHst(Long cookieSeq, int quantity, int amount, String tid, String cid);
}
