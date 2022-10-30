package project.toy.webtoon_copy.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.toy.webtoon_copy.cookiehst.CookieHstDto;
import project.toy.webtoon_copy.kakaopay.KakaoPay;
import project.toy.webtoon_copy.kakaopay.KakaoPayApprovalVo;
import project.toy.webtoon_copy.kakaopay.KakaoPayDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cookie")
public class CookieController {

    @Autowired
    CookieService cookieService;

    @PostMapping("/payment")
    public void paymentToCookie(KakaoPayDto kakaoPayDto, HttpServletResponse response) throws IOException {
        String url = cookieService.paymentToCookie(kakaoPayDto);
        System.out.println("url == "+url);
        response.sendRedirect(url);
    }

    @GetMapping("/kakaopay-success") // RestFul URL 규칙
    public Map<String, CookieDto> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        CookieDto result = cookieService.kakaoPaySuccess(pg_token);
        Map<String, CookieDto> resultMap = new HashMap<>();
        resultMap.put("res", result);
        return resultMap;
    }

    @PostMapping("/kakaopay-cancel")
    public String kakaoPayCancel(@RequestParam("cookieHstSeq") Long cookieHstSeq) {
        String resultCode = cookieService.kakaoPayCancel(cookieHstSeq);
        return resultCode;
    }

    @PutMapping("/use")
    public Map<String, String> use(@RequestParam("userSeq") Long userSeq, @RequestParam("cookieValue") String cookieValue) {
        Map<String, String> resultMap = cookieService.use(userSeq, cookieValue);    // 명칭변경
        return resultMap;
    }
}
