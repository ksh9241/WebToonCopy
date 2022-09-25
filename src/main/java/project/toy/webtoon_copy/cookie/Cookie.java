package project.toy.webtoon_copy.cookie;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Cookie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cookieSeq;
    @NotNull
    Long userSeq;
    @ColumnDefault("0")
    Integer cookieCount;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;
}
