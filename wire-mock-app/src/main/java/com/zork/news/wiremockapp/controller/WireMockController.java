package com.zork.news.wiremockapp.controller;

import com.zork.news.wiremockapp.service.WireMockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wire")
public class WireMockController {

    @Autowired
    WireMockServiceImpl wireMockService;

    @GetMapping(value="/stripe")
    public void getDetails()
    {
        wireMockService.getDetails();
    }

}
