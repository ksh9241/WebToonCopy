package project.toy.webtoon_copy.cookie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.kakaopay.KakaoPay;
import project.toy.webtoon_copy.kakaopay.KakaoPayApprovalVo;
import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.util.CheckUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CookieServiceImpl implements CookieService{

    @Autowired
    ModelMapper mapper;

    @Autowired
    CookieRepository cookieRepository;

    @Autowired
    KakaoPay kakaoPay;

    @Override
    public CookieDto createCookie(Long userSeq) {
        CookieDto cookieDto = new CookieDto();
        cookieDto.setUserSeq(userSeq);
        Cookie cookie = mapper.map(cookieDto, Cookie.class);
        cookieRepository.save(cookie);

        CookieDto resCookieDto = mapper.map(cookie, CookieDto.class);

        return resCookieDto;
    }

    @Override
    public String paymentToCookie(KakaoPayDto kakaoPayDto) {
        return kakaoPay.kakaoPayReady(kakaoPayDto);
    }

    @Override
    public CookieDto kakaoPaySuccess(String pg_token) {
        KakaoPayDto kakaoPayDto = kakaoPay.kakaoPayInfo(pg_token);
        return paymentAfter(kakaoPayDto);
    }

    private CookieDto paymentAfter(KakaoPayDto KakaoPayDto) {
        Cookie findCookie = cookieRepository.findByCookieSeq(KakaoPayDto.getCookieSeq());

        String calcCookieCount = calcCookieCount(findCookie.getCookieCount(), KakaoPayDto.getQuantity());
        findCookie.setCookieCount(calcCookieCount);
        findCookie.setModifyDt(LocalDateTime.now());
        cookieRepository.save(findCookie);

        CookieDto resultDto = mapper.map(findCookie, CookieDto.class);
        return resultDto;
    }

    private String calcCookieCount(String leftVal, String rightVal) {
        if (CheckUtils.isEmpty(leftVal)) leftVal = "0"; // 근본적으로 JPA 디폴트 값이 0으로 들어가게 수정해야됨.
        return "" + (Integer.parseInt(leftVal) + Integer.parseInt(rightVal));
    }
}
