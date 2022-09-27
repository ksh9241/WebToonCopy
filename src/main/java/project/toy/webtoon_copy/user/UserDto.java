package project.toy.webtoon_copy.user;

import com.sun.istack.NotNull;
import lombok.Data;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.cookie.CookieDto;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoon;
import project.toy.webtoon_copy.util.CheckUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {

    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String phoneNum;
    private LocalDateTime createDt = LocalDateTime.now();
    private LocalDateTime modifyDt;
    private CookieDto cookie;
    private List<Comment> myComments;
    private List<LikeWebtoon> myWebtoon;

    // DTO 객체 필수값 체크
    public boolean validation() {
        if (CheckUtils.isEmpty(userId) || CheckUtils.isEmpty(userPwd) || CheckUtils.isEmpty(userName) || CheckUtils.isEmpty(phoneNum)) {
            return true;
        }
        return false;
    }
}
