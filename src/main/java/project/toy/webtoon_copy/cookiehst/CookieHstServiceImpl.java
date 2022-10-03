package project.toy.webtoon_copy.cookiehst;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.user.User;

import java.util.List;

@Service
public class CookieHstServiceImpl implements CookieHstService{
    @Autowired
    CookieHstRepository cookieHstRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<CookieHstDto> findAllbyCookieSeq(Long cookieSeq) {
        return cookieHstRepository.findAllByCookieSeq(cookieSeq);
    }

    @Override
    public CookieHstDto createCookieHst(CookieHstDto cookieHstDto) {
        System.out.println("dto === " + cookieHstDto);
        CookieHst cookieHst = mapper.map(cookieHstDto, CookieHst.class);
        System.out.println("entity == " + cookieHst);
        cookieHstRepository.save(cookieHst);
        CookieHstDto resultDto = mapper.map(cookieHst, CookieHstDto.class);

        return resultDto;
    }
}
