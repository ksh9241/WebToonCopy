package project.toy.webtoon_copy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute UserRequestDto userDto) {
        userService.createUser(userDto.toEntity());
        return "회원가입이 완료되었습니다.";
    }
}
