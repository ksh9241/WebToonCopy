package project.toy.webtoon_copy.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.cookie.CookieResponseDto;
import project.toy.webtoon_copy.cookie.CookieService;

import java.util.Optional;

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
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User userEntity = userOptional.orElseThrow(() -> new UsernameNotFoundException(userId + " Not Found"));

        return new org.springframework.security.core.userdetails.User
                (userEntity.getUserId(), userEntity.getUserPwd(), AuthorityUtils.createAuthorityList(userEntity.getRole().value()));
    }

    public UserResponseDto createUser(User user) {
        // 유저 회원가입
        user.setUserPwd(bCryptPasswordEncoder.encode(user.getUserPwd()));
        userRepository.save(user);

        // 유저 별 쿠키 오브젝트 생성
        CookieResponseDto cookieDto = cookieService.createCookie(user);
        return user.toDto();
    }
}
