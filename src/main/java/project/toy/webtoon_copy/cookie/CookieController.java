package project.toy.webtoon_copy.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.toy.webtoon_copy.kakaopay.KakaoPayRequestDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cookie")
public class CookieController {

    @Autowired
    CookieService cookieService;

    @PostMapping("/payment")
    public void paymentToCookie(KakaoPayRequestDto kakaoPayRequestDto, HttpServletResponse response) throws IOException {
        String url = cookieService.paymentToCookie(kakaoPayRequestDto);
        System.out.println("url == "+url);
        response.sendRedirect(url);
    }

    @GetMapping("/kakaopay-success") // RestFul URL 규칙
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        cookieService.kakaoPaySuccess(pg_token);
        return "성공적으로 결제가 완료되었습니다.";
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
