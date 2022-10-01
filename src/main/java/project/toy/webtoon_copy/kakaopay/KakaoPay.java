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

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    @Value("${kakao-admin}")
    private String adminKey;

    private KakaoPayVo kakaoPayVo;
    private KakaoPayApprovalVo kakaoPayApprovalVo;
    private KakaoPayDto kakaoPayDto;

    public String kakaoPayReady(KakaoPayDto kakaoPayDto) {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        kakaoPayDto.valueSetUp();
        this.kakaoPayDto = kakaoPayDto;

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", kakaoPayDto.getCid());
        params.add("partner_order_id",kakaoPayDto.getPartnerOrderId());
        params.add("partner_user_id",kakaoPayDto.getPartnerUserId());
        params.add("item_name",kakaoPayDto.getItemName());
        params.add("quantity",kakaoPayDto.getQuantity());
        params.add("total_amount",kakaoPayDto.getTotalAmount());
        params.add("tax_free_amount",kakaoPayDto.getTaxFreeAmount());
        params.add("approval_url",kakaoPayDto.getApprovalUrl());
        params.add("cancel_url",kakaoPayDto.getCancelUrl());
        params.add("fail_url",kakaoPayDto.getFailUrl());

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayVo = restTemplate
                    .postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayVo.class);
            return kakaoPayVo.getRedirectPcUrl();
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";
    }

    public KakaoPayApprovalVo kakaoPayInfo(String pg_token) {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayVo.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", "2100");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVo = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVo.class);

            return kakaoPayApprovalVo;

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
