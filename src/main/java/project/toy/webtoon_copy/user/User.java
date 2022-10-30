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
import project.toy.webtoon_copy.util.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

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
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne
    private Cookie cookie;

//    @OneToMany(mappedBy = "user")
//    private List<LikeWebtoon> likeWebtoons = new ArrayList<>();

}
