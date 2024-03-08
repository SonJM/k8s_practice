package com.practice.ordersystem.domain.Item.Controller;

import com.practice.ordersystem.domain.Item.DTO.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MsaTestController {
    private final RestTemplate restTemplate;
    private final String MEMBER_API = "http://jm-member-service/";
    @Autowired
    public MsaTestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test/findMember")
    public MemberDto findMember(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println("item 서버에 있는 token값: " + token);

        String url = MEMBER_API + "member/myInfo";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, MemberDto.class).getBody();
    }
}
