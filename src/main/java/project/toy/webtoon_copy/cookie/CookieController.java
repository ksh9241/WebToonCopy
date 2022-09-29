package project.toy.webtoon_copy.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cookie")
public class CookieController {

    @Autowired
    CookieService cookieService;

//    결제 이력에 대한 부분은 Hst 엔티티 하나 더 만들어야 될 듯
//    @GetMapping("/findPaymentHst")
//    public List<CookieDto> findPaymentHst(Long cookieSeq) {
//        return cookieService.findPaymentHst(cookieSeq);
//    }

    // 내일 할일
    // 1. 카카오페이 오픈 api 구현 후 쿠키 충전 서비스 개발
}
