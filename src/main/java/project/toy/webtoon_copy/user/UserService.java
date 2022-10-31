package project.toy.webtoon_copy.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRequestDto createUser(UserRequestDto userDto);
}
