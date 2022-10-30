package project.toy.webtoon_copy.kakaopay;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class KakaoPayVo {
    private String tid;
    private String cid;
    private String next_redirect_pc_url;
    private Date created_at;
    private String status;
}
