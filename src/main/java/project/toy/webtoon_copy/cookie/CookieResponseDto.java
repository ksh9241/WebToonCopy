package project.toy.webtoon_copy.cookie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import project.toy.webtoon_copy.user.UserResponseDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CookieResponseDto {
    Long cookieSeq;
    Long cookieCount;
    UserResponseDto userResponseDto;
}
