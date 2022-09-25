package project.toy.webtoon_copy.customer;

import com.sun.istack.NotNull;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.likewebtoon.LikeWebtoon;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Users")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userSeq;
    @NotNull
    String userId;
    @NotNull
    String userPwd;
    @NotNull
    String userName;
    @NotNull
    Integer phoneNum;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;

    // 얘네가 엔티티엔 필요없지 않나? 각 값의 PK값만 있으면 되는거 아닌가?
//    Cookie cookie;
//    Comment myComments;
//    LikeWebtoon myWebtoon;

}
