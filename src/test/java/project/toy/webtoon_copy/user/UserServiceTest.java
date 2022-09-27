package project.toy.webtoon_copy.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    UserDto userDto;
    @BeforeEach
    void setup() {
        userDto = new UserDto();
        userDto.setUserId("id");
        userDto.setUserPwd("pwd");
        userDto.setUserName("name");
        userDto.setPhoneNum("01012341234");

    }

    @Test
    void validationToDto_성공() {
        assertThat(userDto.validation()).isEqualTo(false);
    }

    @Test
    @Disabled
    void validationToDto_실패() {
        assertThat(userDto.validation()).isEqualTo(true);
    }

}
