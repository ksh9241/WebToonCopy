package project.toy.webtoon_copy.cookiehst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import project.toy.webtoon_copy.cookie.CookieDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CookieHstController {

    @Autowired
    CookieHstService cookieHstService;

    @GetMapping
    public Map<String, List<CookieHstDto>> findAllCookieHst (Long cookieSeq) {
        List<CookieHstDto> cookieHst = cookieHstService.findAllbyCookieSeq(cookieSeq);
        Map<String, List<CookieHstDto>> resultMap = new HashMap<>();
        resultMap.put("list", cookieHst);
        return resultMap;
    }
}
