package project.toy.webtoon_copy.cookiehst;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CookieHstServiceImpl implements CookieHstService{
    @Autowired
    CookieHstRepository cookieHstRepository;

    @Override
    @Transactional(readOnly = true)
    /**
     * @Description 쿠키 시퀀스로 모든 이력 가져오기
     * */
    public List<CookieHstResponseDto> findAllbyCookieSeq(Long cookieSeq) {
        List<CookieHst> cookieHst = cookieHstRepository.findAllByCookieSeq(cookieSeq);

        return cookieHst.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

    @Override
    /**
     * @Description 쿠키 이력 생성
     * */
    public CookieHstResponseDto createCookieHst(CookieHst cookieHst) {
        cookieHstRepository.save(cookieHst);
        return cookieHst.toDto();
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
