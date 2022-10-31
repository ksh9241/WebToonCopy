package project.toy.webtoon_copy.webtoon;

import lombok.*;
import project.toy.webtoon_copy.util.DayOfWeek;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebtoonRequestDto {

    Long webtoonSeq;
    String webtoonName;
    String artist;
    DayOfWeek dayOfWeek;
    Float grade;
//    LocalDateTime createDt = LocalDateTime.now();
//    LocalDateTime modifyDt;
    String fileName;
    String originFileName;

    public Webtoon toEntity() {
        return Webtoon.builder()
                .webtoonSeq(webtoonSeq)
                .webtoonName(webtoonName)
                .artist(artist)
                .dayOfWeek(dayOfWeek)
                .grade(grade)
                .fileName(fileName)
                .originFileName(originFileName)
                .build();
    }
}
