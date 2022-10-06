package project.toy.webtoon_copy.webtoon;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.util.DayOfWeek;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WebtoonDto {

    Long webtoonSeq;
    String webtoonName;
    String artist;
    DayOfWeek dayOfWeek;
    Integer grade;
    LocalDateTime createDt;
    LocalDateTime modifyDt;
    String fileName;
    String originFileName;
}
