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
    public Map<String, UserDto> createUser(@ModelAttribute UserDto userDto) {
        UserDto resUserDto = userService.createUser(userDto);
        Map<String, UserDto> map = new HashMap<>();
        map.put("user", resUserDto);
        return map;
    }
    
}
