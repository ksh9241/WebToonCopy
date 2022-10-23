package project.toy.webtoon_copy.cookiehst;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CookieHstServiceImpl implements CookieHstService{
    @Autowired
    CookieHstRepository cookieHstRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<CookieHstDto> findAllbyCookieSeq(Long cookieSeq) {
        List<CookieHst> cookieHst = cookieHstRepository.findAllByCookieSeq(cookieSeq);

        List<CookieHstDto> resultList = cookieHst.stream().map(entity -> mapper.map(entity, CookieHstDto.class)).collect(Collectors.toList());
        return resultList;
//        return null;
    }

    @Override
    public CookieHstDto createCookieHst(CookieHstDto cookieHstDto) {
        CookieHst cookieHst = mapper.map(cookieHstDto, CookieHst.class);
        cookieHstRepository.save(cookieHst);
        CookieHstDto resultDto = mapper.map(cookieHst, CookieHstDto.class);

        return resultDto;
    }
}
