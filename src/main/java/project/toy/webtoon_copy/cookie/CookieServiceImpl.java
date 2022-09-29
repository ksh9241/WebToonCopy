package project.toy.webtoon_copy.cookie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookieServiceImpl implements CookieService{

    @Autowired
    ModelMapper mapper;

    @Autowired
    CookieRepository cookieRepository;

    @Override
    public CookieDto createCookie(Long userSeq) {
        CookieDto cookieDto = new CookieDto();
        cookieDto.setUserSeq(userSeq);
        Cookie cookie = mapper.map(cookieDto, Cookie.class);
        cookieRepository.save(cookie);

        CookieDto resCookieDto = mapper.map(cookie, CookieDto.class);

        return resCookieDto;
    }
}
