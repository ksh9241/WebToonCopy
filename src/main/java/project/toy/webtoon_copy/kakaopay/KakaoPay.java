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
import project.toy.webtoon_copy.cookie.CookieDto;
import project.toy.webtoon_copy.cookie.CookieRepository;
import project.toy.webtoon_copy.cookie.CookieService;
import project.toy.webtoon_copy.cookiehst.CookieHstDto;
import project.toy.webtoon_copy.cookiehst.CookieHstService;
import project.toy.webtoon_copy.cookiehst.PaymentCode;
import project.toy.webtoon_copy.util.CheckUtils;

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

    @Autowired
    private CookieHstService cookieHstService;

    @Autowired
    private CookieRepository cookieRepository;

    @Autowired
    ModelMapper mapper;

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
        RestTemplate restTemplate = new RestTemplate();
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", kakaoPayDto.getCid());
        params.add("tid", kakaoPayVo.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", kakaoPayDto.getTotalAmount());

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVo = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVo.class);
            if (successPayment()) {
                createCookieHst();
            }

            return kakaoPayDto;

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String kakaoPayCancel(CookieHstDto cookieHstDto) {
        RestTemplate restTemplate = new RestTemplate();

        cookieHstDto = findCookieInCookieDto(cookieHstDto);
        if (cancelCookieCountCheck(cookieHstDto)) {
            return "쿠키 개수가 부족합니다.";
        }

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        String cancelAmount = String.valueOf(cookieHstDto.getAmount());
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", cookieHstDto.getCid());
        params.add("tid",cookieHstDto.getTid());
        params.add("cancel_amount",cancelAmount);
        params.add("cancel_tax_free_amount",cancelAmount.substring(0, cancelAmount.length() - 1));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            /**
             * kakaoPayVo 의 변수명을 언더바표기법으로 인스턴스를 변경없이 생성해야 한다.
             * */
            kakaoPayVo = restTemplate
                    .postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayVo.class);

            if ("CANCEL_PAYMENT".equals(kakaoPayVo.getStatus())) {
                System.out.println("여기서 이력 만료 및 신규 이력 생성");
                System.out.println("실제 쿠키 개수 처리");
            }

            return kakaoPayVo.getStatus();
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";
    }

    private boolean successPayment() {
        return !CheckUtils.isEmpty(kakaoPayApprovalVo.getAid());
    }

    private void createCookieHst() {
        CookieHstDto cookieHstDto = new CookieHstDto();
        cookieHstDto = findCookieInCookieDto(cookieHstDto);
        cookieHstDto.setPaymentSttusCd(PaymentCode.A);
        cookieHstDto.setAmount(kakaoPayApprovalVo.getAmount().getTotal());
        cookieHstDto.setQuantity(kakaoPayApprovalVo.getQuantity());
        cookieHstDto.setTid(kakaoPayVo.getTid());
        cookieHstService.createCookieHst(cookieHstDto);
    }

    private CookieHstDto findCookieInCookieDto(CookieHstDto cookieHstDto) {
        Cookie cookie = cookieRepository.findByCookieSeq(cookieHstDto.getCookie().getCookieSeq());
        cookieHstDto.setCookie(mapper.map(cookie, CookieDto.class));
        return cookieHstDto;
    }

    private boolean cancelCookieCountCheck(CookieHstDto cookieHstDto) {
        return cookieHstDto.getQuantity() > cookieHstDto.getCookie().getCookieCount();
    }

    private void cancelCookieHst() {

    }



}
