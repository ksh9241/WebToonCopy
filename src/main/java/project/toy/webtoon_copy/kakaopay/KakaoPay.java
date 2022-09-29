package project.toy.webtoon_copy.kakaopay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Service
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    @Value("${kakao-admin}")
    private String adminKey;

    public String kakaoPayReady() {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id","1001");
        params.add("partner_user_id","gorany");
        params.add("item_name","아이폰14");
        params.add("quantity","1");
        params.add("total_amount","5500");
        params.add("tax_free_amount","550");
        params.add("approval_url","http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url","http://localhost:8080/kakaoPayCancel");
        params.add("fail_url","http://localhost:8080/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/pay";
    }
}
