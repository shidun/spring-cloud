package com.imooc.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 12:45
 */
@RestController
public class RefreshController {

    @RequestMapping("/refresh")
    public void refresh() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8080/actuator/bus-refresh",
                request, String.class);
    }
}
