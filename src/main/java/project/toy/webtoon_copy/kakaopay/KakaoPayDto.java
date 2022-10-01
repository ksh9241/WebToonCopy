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
public class KakaoPayDto {

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

    public void valueSetUp() {
        if(CheckUtils.isEmpty(cid)) cid = "TC0ONETIME";
        if(CheckUtils.isEmpty(partnerOrderId)) partnerOrderId = "1001";
        if(CheckUtils.isEmpty(partnerUserId)) partnerUserId = "gorany";
        if(CheckUtils.isEmpty(itemName)) itemName = "아이폰14";
        if(CheckUtils.isEmpty(quantity)) quantity = "1";
        if(CheckUtils.isEmpty(totalAmount)) totalAmount = "5500";
        if(CheckUtils.isEmpty(taxFreeAmount)) taxFreeAmount = "550";
        if(CheckUtils.isEmpty(approvalUrl)) approvalUrl = "http://localhost:8080/cookie/kakaoPaySuccess";
        if(CheckUtils.isEmpty(cancelUrl)) cancelUrl = "http://localhost:8080/cookie/kakaoPayCancel";
        if(CheckUtils.isEmpty(failUrl)) failUrl = "http://localhost:8080/cookie/kakaoPaySuccessFail";
    }
}
