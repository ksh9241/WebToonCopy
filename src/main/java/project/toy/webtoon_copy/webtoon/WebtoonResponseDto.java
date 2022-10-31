package project.toy.webtoon_copy.webtoon;

import lombok.*;
import project.toy.webtoon_copy.util.DayOfWeek;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebtoonResponseDto {

    Long webtoonSeq;
    String webtoonName;
    String artist;
    DayOfWeek dayOfWeek;
    Float grade;
    String fileName;
    String originFileName;
}
