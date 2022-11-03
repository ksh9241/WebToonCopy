package project.toy.webtoon_copy.cookie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.webtoon_copy.cookiehst.CookieHstRequestDto;
import project.toy.webtoon_copy.cookiehst.CookieHstResponseDto;
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
import java.util.Optional;

@Service
@Transactional
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
    public CookieResponseDto createCookie(User user) {
        Cookie cookie = new Cookie();
        cookie.setUser(user);
        cookieRepository.save(cookie);

        return cookie.toDto();
    }

    @Override
    public String paymentToCookie(KakaoPayDto kakaoPayDto) {
        return kakaoPay.kakaoPayReady(kakaoPayDto);
    }

    @Override
    public CookieRequestDto kakaoPaySuccess(String pg_token) {
        KakaoPayDto kakaoPayDto = kakaoPay.kakaoPayInfo(pg_token);
        return paymentAfter(kakaoPayDto);
    }

    @Override
    @Transactional
    /**
     * @Descripton 쿠키를 사용합니다.
     * */
    public void use(Long userSeq, int cookieValue) {

        Optional<User> findUser = userRepository.findByUserSeq(userSeq);
        User user = findUser.orElseThrow(() -> new UsernameNotFoundException("유저 정보가 없습니다."));  // 잘못된 유저 정보일 경우

        // 쿠키 개수 체크
        user.getCookie().useCookie(cookieValue);
        userRepository.save(user);
    }

    // 여기서는 결제취소부르고 따로 쿠키 개수차감 및 쿠키이력생성 서비스 호출
    @Override
//    public String kakaoPayCancel(Long cookieHstSeq) {
    public void cancelCookieHst(Long cookieHstSeq) {
        CookieHstResponseDto cookieHstResponseDto = cookieHstService.cancelCookieHst(cookieHstSeq);

        String cid = cookieHstResponseDto.getCid();
        String tid = cookieHstResponseDto.getTid();
        int amount = cookieHstResponseDto.getAmount();
        kakaoPay.kakaoPayCancel(cid, tid, amount);

        return null;
    }

    //
    private boolean updateCookieCount(Cookie cookie, String cookieValue) {
        boolean result = true;

        if (CheckUtils.isEmpty(cookie.getCookieCount()) || cookie.getCookieCount() < Integer.parseInt(cookieValue)) {
            return false;
        }

        Long afterCookieCount = cookie.getCookieCount() - Integer.parseInt(cookieValue);
        cookie.setCookieCount(afterCookieCount);
        cookie.setModifyAt(LocalDateTime.now());
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
