package project.toy.webtoon_copy.cookie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.webtoon_copy.cookiehst.CookieHstRequestDto;
import project.toy.webtoon_copy.cookiehst.CookieHstService;
import project.toy.webtoon_copy.cookiehst.PaymentCode;
import project.toy.webtoon_copy.kakaopay.KakaoPay;
import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserRequestDto;
import project.toy.webtoon_copy.user.UserRepository;
import project.toy.webtoon_copy.util.CheckUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CookieServiceImpl implements CookieService{

    @Autowired
    ModelMapper mapper;

    @Autowired
    CookieRepository cookieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    KakaoPay kakaoPay;

    @Autowired
    CookieHstService cookieHstService;

    @Override
    public Cookie createCookie(UserRequestDto userDto) {
        CookieRequestDto cookieDto = new CookieRequestDto();
        cookieDto.setUser(userDto);
        Cookie cookie = mapper.map(cookieDto, Cookie.class);
        cookieRepository.save(cookie);

//        CookieDto resCookieDto = mapper.map(cookie, CookieDto.class);

        return cookie;
    }

    @Override
    public String paymentToCookie(KakaoPayDto kakaoPayDto) {
        return kakaoPay.kakaoPayReady(kakaoPayDto);
    }

    @Override
    @Transactional
    public CookieRequestDto kakaoPaySuccess(String pg_token) {
        KakaoPayDto kakaoPayDto = kakaoPay.kakaoPayInfo(pg_token);
        return paymentAfter(kakaoPayDto);
    }

    @Override
    @Transactional
//    public Map<String, String> useCookie(Long userSeq, String cookieValue) {
        public void useCookie(Long userSeq, String cookieValue) { // if 조건에 대한 부분들은 예외로 던지고 아무 예외 없을 시 성공
        Map<String, String> resultMap = new HashMap<>();

        User user = userRepository.findByUserSeq(userSeq); // Optional로 바꾸기
        // if 조건 다 지우고 상태 체크는 엔티티에서 직접 처리하고 예외 아닐 시 status 200 던짐
//        if (!CheckUtils.isEmptyByObject(user)) {
            //Cookie cookie = cookieRepository.findByCookieSeq(user.getCookie().getCookieSeq());
//            if (updateCookieCount(cookie, cookieValue)) { // 이 부분 CookieEntity에서 메서드로 처리
//                resultMap.put("msg", "성공적으로 결제가 완료되었습니다.");
//            } else {
//                resultMap.put("msg", "잔액이 부족합니다.");
//            }
//        } else {
//            resultMap.put("msg", "유저 정보가 없습니다.");
//        }
//        return resultMap;
    }

    @Override
//    public String kakaoPayCancel(Long cookieHstSeq) {
    public String cancelCookieHst(Long cookieHstSeq) {
        CookieHstRequestDto cookieHstDto = cookieHstService.findByCookieHstSeq(cookieHstSeq);
        return kakaoPay.kakaoPayCancel(cookieHstDto);
    }

    //
    private boolean updateCookieCount(Cookie cookie, String cookieValue) {
        boolean result = true;

        if (CheckUtils.isEmpty(cookie.getCookieCount()) || cookie.getCookieCount() < Integer.parseInt(cookieValue)) {
            return false;
        }

        Long afterCookieCount = cookie.getCookieCount() - Integer.parseInt(cookieValue);
        cookie.setCookieCount(afterCookieCount);
        cookie.setModifyDt(LocalDateTime.now());
        Cookie resultCookie = cookieRepository.save(cookie);

        cookieHstService.createCookieHst(initCookieHstDto(resultCookie, cookieValue));

        return result;
    }

    private CookieHstRequestDto initCookieHstDto(Cookie cookie, String cookieValue) {
        CookieRequestDto cookieDto = mapper.map(cookie, CookieRequestDto.class);

        CookieHstRequestDto cookieHstDto = new CookieHstRequestDto();
        cookieHstDto.setCookie(cookieDto);
        cookieHstDto.setQuantity(Integer.parseInt(cookieValue));
        cookieHstDto.setPaymentSttusCd(PaymentCode.S);
        return cookieHstDto;
    }

//    @Override
//    public Cookie findByCookieSeq(Long cookieSeq) {
//        return cookieRepository.findByCookieSeq(cookieSeq);
//    }

    private CookieRequestDto paymentAfter(KakaoPayDto KakaoPayDto) {
        Cookie findCookie = cookieRepository.findByCookieSeq(KakaoPayDto.getCookieSeq());

        Long calcCookieCount = calcCookieCount(findCookie.getCookieCount(), KakaoPayDto.getQuantity());
        findCookie.setCookieCount(calcCookieCount);
        findCookie.setModifyDt(LocalDateTime.now());
        cookieRepository.save(findCookie);

        CookieRequestDto resultDto = mapper.map(findCookie, CookieRequestDto.class);
        return resultDto;
    }

    private Long calcCookieCount(Long leftVal, String rightVal) {
        if (CheckUtils.isEmpty(leftVal) || leftVal == 0) leftVal = 0L; // 근본적으로 JPA 디폴트 값이 0으로 들어가게 수정해야됨.
        return leftVal + Long.parseLong(rightVal);
    }
}
