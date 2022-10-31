package project.toy.webtoon_copy.cookie;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CookieRequestDto {
    Long cookieSeq;
    Long cookieCount;
    LocalDateTime createDt = LocalDateTime.now();
    LocalDateTime modifyDt;
//    UserDto userDto;

//    @JsonManagedReference
//    List<CookieHstDto> cookieHst;

    public Cookie toEntity() {
        return Cookie.builder()
                .cookieSeq(cookieSeq)
                .cookieCount(cookieCount)
                .build();
    }
}
