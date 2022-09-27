package project.toy.webtoon_copy.cookie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CookieDto {
    Long cookieSeq;
    Long userSeq;
    String cookieCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
}
