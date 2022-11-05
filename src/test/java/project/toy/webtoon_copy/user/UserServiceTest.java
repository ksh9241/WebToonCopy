package project.toy.webtoon_copy.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.toy.webtoon_copy.util.Role;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    UserRequestDto userDto;
    @BeforeEach
    void setup() {
        userDto = new UserRequestDto();
        userDto.setUserId("id");
        userDto.setUserPwd("pwd");
        userDto.setUserName("name");
        userDto.setPhoneNum("01012341234");
        userDto.setRole(Role.ROLE_USER);
    }

    @Test
    void 회원가입테스트() {
        UserResponseDto userResponseDto = userService.createUser(userDto.toEntity());
        assertNotNull(userResponseDto);
    }
}
