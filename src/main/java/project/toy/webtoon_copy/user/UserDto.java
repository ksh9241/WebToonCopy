package project.toy.webtoon_copy.user;

import lombok.Data;
import project.toy.webtoon_copy.comments.CommentDto;
import project.toy.webtoon_copy.cookie.CookieDto;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoonDto;
import project.toy.webtoon_copy.util.CheckUtils;
import project.toy.webtoon_copy.util.Role;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {

    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String phoneNum;
    private Role role;
    private LocalDateTime createDt = LocalDateTime.now();
    private LocalDateTime modifyDt;
    private List<CommentDto> comments;
    private CookieDto cookie;
    private List<LikeWebtoonDto> likeWebtoons;

    // DTO 객체 필수값 체크
    public boolean validation() {
        if (CheckUtils.isEmpty(userId) || CheckUtils.isEmpty(userPwd) || CheckUtils.isEmpty(userName) || CheckUtils.isEmpty(phoneNum)) {
            return true;
        }
        return false;
    }
}
