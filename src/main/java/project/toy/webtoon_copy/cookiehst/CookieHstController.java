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
    public Map<String, List<CookieHstResponseDto>> findAllCookieHst (Long cookieSeq) {
        List<CookieHstResponseDto> cookieHst = cookieHstService.findAllbyCookieSeq(cookieSeq);
        Map<String, List<CookieHstResponseDto>> resultMap = new HashMap<>();
        resultMap.put("list", cookieHst);
        return resultMap;
    }
}
