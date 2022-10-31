package project.toy.webtoon_copy.cookiehst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CookieHstController {

    @Autowired
    CookieHstService cookieHstService;

    @GetMapping
    public Map<String, List<CookieHstRequestDto>> findAllCookieHst (Long cookieSeq) {
        List<CookieHstRequestDto> cookieHst = cookieHstService.findAllbyCookieSeq(cookieSeq);
        Map<String, List<CookieHstRequestDto>> resultMap = new HashMap<>();
        resultMap.put("list", cookieHst);
        return resultMap;
    }
}
