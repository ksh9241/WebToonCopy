package project.toy.webtoon_copy.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/kakaoPaySuccess")
    public Map<String, CookieDto> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        CookieDto result = cookieService.kakaoPaySuccess(pg_token);
        Map<String, CookieDto> resultMap = new HashMap<>();
        resultMap.put("res", result);
        return resultMap;
    }

    // 내일 할일
    // 1. 카카오페이 오픈 api 구현 후 쿠키 충전 서비스 개발
}
