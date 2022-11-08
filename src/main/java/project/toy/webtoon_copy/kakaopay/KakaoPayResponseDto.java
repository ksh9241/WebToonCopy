package project.toy.webtoon_copy.kakaopay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoPayResponseDto {
    private Long cookieSeq;
    private int quantity;
    private AmountVO amount;
    private String tid;
    private String cid;
}
