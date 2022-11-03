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

    @Autowired
    ModelMapper mapper;

    @Override
    public List<CookieHstRequestDto> findAllbyCookieSeq(Long cookieSeq) {
        List<CookieHst> cookieHst = cookieHstRepository.findAllByCookieSeq(cookieSeq);

        List<CookieHstRequestDto> resultList = cookieHst.stream().map(entity -> mapper.map(entity, CookieHstRequestDto.class)).collect(Collectors.toList());
        return resultList;
//        return null;
    }

    @Override
    public CookieHstRequestDto createCookieHst(CookieHstRequestDto cookieHstDto) {
        CookieHst cookieHst = mapper.map(cookieHstDto, CookieHst.class);
        CookieHst resultCookieHst = cookieHstRepository.save(cookieHst);
        CookieHstRequestDto resultDto = mapper.map(resultCookieHst, CookieHstRequestDto.class);

        return resultDto;
    }

    @Override
    public CookieHstResponseDto cancelCookieHst(Long cookieHstSeq) {
        CookieHst cookieHst = cookieHstRepository.findByCookieHstSeq(cookieHstSeq);
        cookieHst.setEfctFnsAt();

        return resCookieHstDto;
    }

    @Override
    public CookieHstRequestDto cancelCookieHst(CookieHstRequestDto cookieHstDto) {
        cookieHstDto.setEfctFnsAt(LocalDateTime.now());
        cookieHstDto.setModifyAt(LocalDateTime.now());
        CookieHst cookieHst = mapper.map(cookieHstDto, CookieHst.class);
        cookieHstRepository.save(cookieHst);
        return null;
    }
}
