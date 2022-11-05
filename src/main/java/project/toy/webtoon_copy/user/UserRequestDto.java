package project.toy.webtoon_copy.user;

import lombok.Getter;
import lombok.Setter;
import project.toy.webtoon_copy.cookie.CookieRequestDto;
import project.toy.webtoon_copy.util.Role;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequestDto {
//

    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String phoneNum;
    private Role role;
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;
//    private List<CommentResponseDto> comments;
//    @JsonManagedReference
    private CookieRequestDto cookieDto;
//    private List<LikeWebtoonDto> likeWebtoons;

    // 신규가입 시 쿠키값 없으니 생성해주는데 더 좋은 방법이 있나?
    public UserRequestDto() {
        if (cookieDto == null) {
            cookieDto = new CookieRequestDto();
        }
    }

    public User toEntity() {
        return User.builder()
                .userSeq(userSeq)
                .userId(userId)
                .userPwd(userPwd)
                .userName(userName)
                .phoneNum(phoneNum)
                .role(role)
                .cookie(cookieDto.toEntity())
                .build();
    }
}
