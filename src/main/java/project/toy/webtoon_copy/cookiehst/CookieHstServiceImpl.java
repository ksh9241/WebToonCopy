package project.toy.webtoon_copy.cookiehst;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookieHstServiceImpl implements CookieHstService{
    @Autowired
    CookieHstRepository cookieHstRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CookieHstDto> findAllbyCookieSeq(Long cookieSeq) {
        return cookieHstRepository.findAllByCookieSeq(cookieSeq);
    }

    @Override
    public CookieHstDto createCookieHst(CookieHstDto cookieHstDto) {
        CookieHst cookieHst = modelMapper.map(cookieHstDto, CookieHst.class);
        cookieHstRepository.save(cookieHst);
        CookieHstDto resultDto = modelMapper.map(cookieHst, CookieHstDto.class);

        return resultDto;
    }
}
