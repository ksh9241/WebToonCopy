package project.toy.webtoon_copy.kakaopay;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cookie")
public class KakaoPayController {

    @Autowired
    private KakaoPay kakaoPay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(KakaoPayDto kakaoPayDto) {
        String url = kakaoPay.kakaoPayReady(kakaoPayDto);
        System.out.println("url == " + url);
        return "redirect:" + url;
    }

    @GetMapping("/kakaoPaySuccess")
    @ResponseBody
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        KakaoPayApprovalVo vo = kakaoPay.kakaoPayInfo(pg_token);
        System.out.println("res info == " + vo);
//        model.addAttribute("info", vo);
    }

}
