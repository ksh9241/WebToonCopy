package project.toy.webtoon_copy.kakaopay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.toy.webtoon_copy.util.CheckUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KakaoPayRequestDto {

    private Long cookieSeq;
    private String cid;
    private String partnerOrderId;
    private String partnerUserId;
    private String itemName;
    private String quantity;
    private String totalAmount;
    private String taxFreeAmount;
    private String approvalUrl;
    private String cancelUrl;
    private String failUrl;

    public void init() {
        if(CheckUtils.isEmpty(partnerOrderId)) partnerOrderId = "1001";
        if(CheckUtils.isEmpty(partnerUserId)) partnerUserId = "gorany";
        if(CheckUtils.isEmpty(itemName)) itemName = "쿠키";
        if(CheckUtils.isEmpty(quantity)) quantity = "1";
        if(CheckUtils.isEmpty(totalAmount)) totalAmount = String.valueOf(Integer.parseInt(quantity) * 100);
        if(CheckUtils.isEmpty(taxFreeAmount)) taxFreeAmount = totalAmount.substring(0, totalAmount.length() - 1);
    }
}
