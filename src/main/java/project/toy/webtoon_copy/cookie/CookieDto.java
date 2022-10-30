package project.toy.webtoon_copy.cookie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import project.toy.webtoon_copy.cookiehst.CookieHst;
import project.toy.webtoon_copy.cookiehst.CookieHstDto;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    CookieDto {
    Long cookieSeq;
    Long cookieCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
    @JsonBackReference
    UserDto user;

//    @JsonManagedReference
    List<CookieHstDto> cookieHst;
}
