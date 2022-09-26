package project.toy.webtoon_copy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Map<String, UserDto> createUser(@ModelAttribute UserDto userDto) {
        UserDto resUserDto = userService.createUser(userDto);
        Map<String, UserDto> map = new HashMap<>();
        map.put("user", resUserDto);
        return map;
    }
}
