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
    public Map<String, UserRequestDto> createUser(@ModelAttribute UserRequestDto userDto) {
        UserRequestDto resUserDto = userService.createUser(userDto);
        Map<String, UserRequestDto> map = new HashMap<>();
        map.put("user", resUserDto);
        return map;
    }
}
