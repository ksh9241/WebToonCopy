package project.toy.webtoon_copy.kakaopay;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import project.toy.webtoon_copy.cookie.Cookie;
import project.toy.webtoon_copy.cookie.CookieRequestDto;
import project.toy.webtoon_copy.cookie.CookieRepository;
import project.toy.webtoon_copy.cookiehst.CookieHstRequestDto;
import project.toy.webtoon_copy.cookiehst.CookieHstService;
import project.toy.webtoon_copy.cookiehst.PaymentCode;
import project.toy.webtoon_copy.util.CheckUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
// 카카오페이 기능에서 결제 관련 내용 빼고 다 다른 곳으로 옮겨야 함.
// DTO로 받는게 아닌 command나 혹은 매개변수에서 필요한 값만 받기
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    @Value("${kakao-admin}")
    private String adminKey;
    @Value("${kakao-cid}")
    private String cid;
    @Value("${kakao-approvalUrl}")
    private String approvalUrl;
    @Value("${kakao-cancelUrl}")
    private String cancelUrl;
    @Value("${kakao-failUrl}")
    private String failUrl;

    // 싱글톤이라 이전 결과에 대한 데이터가 남아있어서 문제됨.
    private KakaoPayVo kakaoPayVo;
    private KakaoPayApprovalVo kakaoPayApprovalVo;
    private KakaoPayDto kakaoPayDto;

    // 싱글톤으로 사용해도 문제 없음.
    private RestTemplate restTemplate = new RestTemplate();

    // 생성자 주입에 대한 부분 복습

    public String kakaoPayReady(KakaoPayDto kakaoPayDto) {

        kakaoPayDto.valueSetUp(cid, approvalUrl, cancelUrl, failUrl);
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

        HttpEntity<MultiValueMap<String, String>> body = initRestTemplate(params);

        try {
            /**
             * kakaoPayVo 의 변수명을 언더바표기법으로 인스턴스를 변경없이 생성해야 한다.
             * */
            kakaoPayVo = restTemplate
                    .postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayVo.class);

            return kakaoPayVo.getNext_redirect_pc_url();
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";
    }

    public KakaoPayDto kakaoPayInfo(String pg_token) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", kakaoPayDto.getCid());
        params.add("tid", kakaoPayVo.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", kakaoPayDto.getTotalAmount());

        HttpEntity<MultiValueMap<String, String>> body = initRestTemplate(params);

        try {
            kakaoPayApprovalVo = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVo.class);
            if (notSuccessPayment()) {
                throw new IllegalStateException();
            }

            return kakaoPayDto;

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean notSuccessPayment() {
        return CheckUtils.isEmpty(kakaoPayApprovalVo.getAid());
    }

    public void kakaoPayCancel(String cid, String tid, int amount) { // Cid, Tid, Amount
        String cancelAmount = String.valueOf(amount);
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", cid);
        params.add("tid",tid);
        params.add("cancel_amount",cancelAmount);
        params.add("cancel_tax_free_amount",cancelAmount.substring(0, cancelAmount.length() - 1));

        HttpEntity<MultiValueMap<String, String>> body = initRestTemplate(params);

        try {
            /**
             * kakaoPayVo 의 변수명을 언더바표기법으로 인스턴스를 변경없이 생성해야 한다.
             * */
            kakaoPayVo = restTemplate
                    .postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayVo.class);

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private HttpEntity<MultiValueMap<String, String>> initRestTemplate(MultiValueMap<String, String> params) {
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return new HttpEntity<MultiValueMap<String, String>>(params, headers);
    }
}
