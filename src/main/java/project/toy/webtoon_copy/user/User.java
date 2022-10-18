package project.toy.webtoon_copy.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.util.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Users")
@Data
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

    @OneToMany(mappedBy = "users")
    List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
