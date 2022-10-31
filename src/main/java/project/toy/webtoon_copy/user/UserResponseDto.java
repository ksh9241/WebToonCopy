package project.toy.webtoon_copy.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.toy.webtoon_copy.cookie.CookieRequestDto;
import project.toy.webtoon_copy.util.Role;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserResponseDto {

    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String phoneNum;
    private Role role;
    private CookieRequestDto cookieDto;
}
