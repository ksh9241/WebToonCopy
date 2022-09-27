package project.toy.webtoon_copy.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userSeq;
    @NotNull
    String userId;
    @NotNull
    String userPwd;
    @NotNull
    String userName;
    @NotNull
    String phoneNum;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;
}
