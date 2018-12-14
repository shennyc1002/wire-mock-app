package com.zork.news.wiremockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

@Service
public class WireMockServiceImpl {



    public ResponseEntity getDetails()
    {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.getForEntity("http://localhost:8877/v1/charges",String.class);
        return response;
    }
}
