package project.toy.webtoon_copy.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.cookie.CookieDto;
import project.toy.webtoon_copy.cookie.CookieService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CookieService cookieService;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userId + ": not found");
        }
        return new org.springframework.security.core.userdetails.User
                (userEntity.getUserId(), userEntity.getUserPwd(), AuthorityUtils.createAuthorityList(userEntity.getRole().value()));
    }

    public UserDto createUser(UserDto userDto) {
        if (userDto.validation()) {
            throw new UsernameNotFoundException("필수값 없음");
        }

        // 유저 회원가입
        User user = mapper.map(userDto, User.class);
        user.setUserPwd(bCryptPasswordEncoder.encode(userDto.getUserPwd()));
        userRepository.save(user);
        UserDto resUserDto = mapper.map(user, UserDto.class);

        // 유저 별 쿠키 오브젝트 생성
        CookieDto cookieDto = cookieService.createCookie(resUserDto.getUserSeq());
        resUserDto.setCookie(cookieDto);

        return resUserDto;
    }
}
