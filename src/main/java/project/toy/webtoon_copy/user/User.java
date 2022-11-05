package project.toy.webtoon_copy.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoon;
import project.toy.webtoon_copy.util.CheckUtils;
import project.toy.webtoon_copy.util.Common;
import project.toy.webtoon_copy.util.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User extends Common {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "USER_SEQ")
    Long userSeq;
    @NotNull
    String userId;
    @NotNull
    String userPwd;
    @NotNull
    String userName;
    @NotNull
    String phoneNum;
    @NotNull @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)    // TransientPropertyValueException 예외 FK로 쓰는 객체가 저장이 안되어있을 때
    private Cookie cookie;

    public UserResponseDto toDto() {
        return UserResponseDto.builder()
                .userSeq(userSeq)
                .userId(userId)
                .userPwd(userPwd)
                .userName(userName)
                .phoneNum(phoneNum)
                .role(role)
                .build();
    }

    public boolean checkNotLogin() {
        return CheckUtils.isEmpty(userId);
    }

//    @OneToMany(mappedBy = "user")
//    private List<LikeWebtoon> likeWebtoons = new ArrayList<>();

}
