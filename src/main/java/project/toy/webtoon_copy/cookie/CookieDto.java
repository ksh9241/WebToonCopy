package project.toy.webtoon_copy.cookie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.toy.webtoon_copy.cookiehst.CookieHst;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CookieDto {
    Long cookieSeq;
    Long cookieCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    @JsonBackReference
    UserDto user;
    List<CookieHst> cookieHst;
}
