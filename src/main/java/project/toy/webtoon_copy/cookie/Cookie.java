package project.toy.webtoon_copy.cookie;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cookie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cookieSeq;
    @NotNull
    Long userSeq;
    @Column(columnDefinition = "varchar(20) default '0'") // default 값 설정하는 거 처리해야 함.
    String cookieCount;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;
}
