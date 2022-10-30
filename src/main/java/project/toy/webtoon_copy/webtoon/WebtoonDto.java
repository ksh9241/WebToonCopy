package project.toy.webtoon_copy.webtoon;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import project.toy.webtoon_copy.util.DayOfWeek;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebtoonDto {

    Long webtoonSeq;
    String webtoonName;
    String artist;
    DayOfWeek dayOfWeek;
    Float grade;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    String fileName;
    String originFileName;
}
