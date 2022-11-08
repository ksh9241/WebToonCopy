package project.toy.webtoon_copy.cookiehst;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.cookie.CookieRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CookieHstServiceImpl implements CookieHstService{
    @Autowired
    CookieHstRepository cookieHstRepository;

    @Autowired
    CookieRepository cookieRepository;

    @Override
    @Transactional(readOnly = true)
    /**
     * @Description 쿠키 시퀀스로 모든 이력 가져오기
     * */
    public List<CookieHstResponseDto> findAllbyCookieSeq(Long cookieSeq) {
        List<CookieHst> cookieHst = cookieHstRepository.findAllByCookieSeq(cookieSeq);

        return cookieHst.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

    /**
     * @Description 쿠키 이력 생성
     * */
    @Override
    public CookieHstResponseDto createCookieHst(Long cookieSeq, int quantity, int amount, String tid, String cid) {
        CookieHst cookieHst = initCookieHst(cookieSeq, quantity, amount, tid, cid);
        cookieHstRepository.save(cookieHst);
        return cookieHst.toDto();
    }

    private CookieHst initCookieHst (Long cookieSeq, int quantity, int amount, String tid, String cid) {
        Cookie cookie = cookieRepository.findByCookieSeq(cookieSeq);
        CookieHst cookieHst = new CookieHst();
        cookieHst.setCookie(cookie);
        cookieHst.setPaymentStatusCd(PaymentCode.PAYMENT);
        cookieHst.setAmount(amount);
        cookieHst.setQuantity(quantity);
        cookieHst.setTid(tid);
        cookieHst.setCid(cid);

        return cookieHst;
    }

    @Override
    /**
     * @Description 쿠키이력시퀀스로 이력 조회 후 결제이력 만료 및 취소이력 생성
     * */
    public CookieHst cancelCookieHst(Long cookieHstSeq) {
        // 결제 이력 만료
        CookieHst cookieHst = cookieHstRepository.findByCookieHstSeq(cookieHstSeq);
        cookieHst.setEfctFnsAt(LocalDateTime.now());
        cookieHstRepository.save(cookieHst);

        // 취소이력 생성
        CookieHst cancelCookieHst = new CookieHst().copyCookieHst(cookieHst);
        cancelCookieHst.setPaymentStatusCd(PaymentCode.CANCEL);

        return cookieHst;
    }
}
