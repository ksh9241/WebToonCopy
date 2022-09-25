package project.toy.webtoon_copy.webtoon;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.util.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Webtoon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long webtoonSeq;
    @NotNull
    String webtoonName;
    @NotNull
    String artist;
    @NotNull
    String dayOfWeek;
    @ColumnDefault("0")
    Integer grade;
    @NotNull
    LocalDateTime createDt;
    LocalDateTime modifyDt;
}
