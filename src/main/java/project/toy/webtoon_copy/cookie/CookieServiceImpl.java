package project.toy.webtoon_copy.cookie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.webtoon_copy.cookiehst.*;
import project.toy.webtoon_copy.kakaopay.KakaoPay;
import project.toy.webtoon_copy.kakaopay.KakaoPayDto;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserRepository;
import project.toy.webtoon_copy.util.CheckUtils;

import java.time.LocalDateTime;
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
    /**
     * @Description 카카오 페이 결제 성공 처리 및 쿠키 개수 증가
     * */
    public void kakaoPaySuccess(String pg_token) {
        KakaoPayDto kakaoPayDto = kakaoPay.kakaoPayInfo(pg_token);
        buyCookie(kakaoPayDto.getCookieSeq(), Integer.parseInt(kakaoPayDto.getQuantity()));
    }

    /**쿠키 구입 시 쿠키 값 변경*/
    private void buyCookie(Long cookieSeq, int cookieCount) {
        Cookie findCookie = cookieRepository.findByCookieSeq(cookieSeq);
        findCookie.buyCookie(cookieCount);

        cookieRepository.save(findCookie);
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
        user.getCookie().updateCookie(cookieValue);
        userRepository.save(user);
    }

    // 결제 취소 시발점이 쿠키 오브젝트가 맞나??
    @Override
    /**
     * @Description 결제취소 부르고 쿠키 개수차감 및 쿠키이력 변경
     * */
    public void cancelCookieHst(Long cookieHstSeq) {
        // 쿠키 이력 만료 및 취소 이력 생성
        CookieHst cookieHst = cookieHstService.cancelCookieHst(cookieHstSeq);

        // 쿠키 개수 취소 처리
        cancelCookie(cookieHst.getCookie(), cookieHst.getQuantity());

        // 카카오페이 결제 취소
        String cid = cookieHst.getCid();
        String tid = cookieHst.getTid();
        int amount = cookieHst.getAmount();
        kakaoPay.kakaoPayCancel(cid, tid, amount);
    }

    //
    private void cancelCookie(Cookie cookie, int cookieValue) {

        cookie.updateCookie(cookieValue);
        cookieRepository.save(cookie);
    }

//    @Override
//    public Cookie findByCookieSeq(Long cookieSeq) {
//        return cookieRepository.findByCookieSeq(cookieSeq);
//    }
}
