package project.toy.webtoon_copy.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.toy.webtoon_copy.kakaopay.KakaoPayDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
    public Map<String, CookieRequestDto> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        CookieRequestDto result = cookieService.kakaoPaySuccess(pg_token);
        Map<String, CookieRequestDto> resultMap = new HashMap<>();
        resultMap.put("res", result);
        return resultMap;
    }

    @PostMapping("/kakaopay-cancel")
    public String kakaoPayCancel(@RequestParam("cookieHstSeq") Long cookieHstSeq) {
        cookieService.cancelCookieHst(cookieHstSeq);
        return "결제 취소가 완료되었습니다.";
    }

    @PutMapping("/use")
    public String use(@RequestParam("userSeq") Long userSeq, @RequestParam("cookieValue") int cookieValue) {
        cookieService.use(userSeq, cookieValue);    // 명칭변경
        return "성공적으로 결제가 완료되었습니다.";
    }
}
