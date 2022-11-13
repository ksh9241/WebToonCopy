package project.toy.webtoon_copy.likewebtoon;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class LikeWebtoonController {

    @Autowired
    LikeWebtoonService likeWebtoonService;

    private Gson gson = new Gson();

    @GetMapping("/find-likewebtoon")
    public List<LikeWebtoonResponseDto> findAllLikeWebtoonByUserSeq(@RequestParam("userSeq") Long userSeq) {
        List<LikeWebtoonResponseDto> responseDto = likeWebtoonService.findAllLikeWebtoonByUserSeq(userSeq);
        return responseDto;
    }

    @PostMapping("/likewebtoon")
    public String likeWebtoon(@RequestParam("userSeq") Long userSeq, @RequestParam("webtoonSeq") Long webtoonSeq) {
        likeWebtoonService.likeWebtoon(userSeq, webtoonSeq);
        return "관심웹툰에 추가되었습니다.";
    }
}
