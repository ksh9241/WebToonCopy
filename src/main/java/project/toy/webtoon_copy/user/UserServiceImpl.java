package project.toy.webtoon_copy.user;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userId + ": not found");
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getUserId(), userEntity.getUserPwd(), new ArrayList<>());
    }

    public UserDto createUser(UserDto userDto) {
        if (userDto.validation()) {
            throw new UsernameNotFoundException("필수값 없음");
        }

        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
        user.setUserPwd(bCryptPasswordEncoder.encode(userDto.getUserPwd()));
        userRepository.save(user);
        UserDto resUserDto = mapper.map(user, UserDto.class);
        return resUserDto;
    }

}
