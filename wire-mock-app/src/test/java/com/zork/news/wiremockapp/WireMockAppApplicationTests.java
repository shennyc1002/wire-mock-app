package com.zork.news.wiremockapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.zork.news.wiremockapp.entity.StripeObject;
import com.zork.news.wiremockapp.service.WireMockServiceImpl;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WireMockAppApplicationTests {

	Logger logger = LoggerFactory.getLogger(WireMockAppApplicationTests.class);
	@ClassRule
	public static WireMockClassRule wireMockClassRule = new WireMockClassRule(8877);
	@Rule
	public WireMockClassRule wireMockRule = wireMockClassRule;

	@Autowired
	WireMockServiceImpl wireMockService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void stripeChargesTest()
	{
		wireMockRule.stubFor(get(urlEqualTo("/v1/charges"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type","application/json")
						.withBody("{" +
								"'id':12323," +
								"'name':'charge'," +
								"'amount':10000" +
								"''captured:false"+
								"}</response>")));

		ResponseEntity response = wireMockService.getDetails();

		logger.error("resposne is "+response);



		assertEquals(200,response.getStatusCode());


	}


	@Test
	public void stripeChargesDataTest()
	{
		wireMockRule.stubFor(get(urlEqualTo("/v1/charges"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type","application/json")
						.withBody("{" +
								"'id':'12323'," +
								"'object':'charge'," +
								"'amount':10000" +
								"''captured:false"+
								"}")));

		ResponseEntity response = wireMockService.getDetails();

		logger.error("resposne is "+response);


		assertEquals(true,response.getBody().toString().contains("'object':'charge'"));


	}

	@Test
	public void stripeChargesIdTest()
	{
		wireMockRule.stubFor(get(urlEqualTo("/v1/charges"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type","application/json")
						.withBody("</response>{" +
								"'id':12323," +
								"'object':'charge'," +
								"'amount':10000" +
								"''captured:false"+
								"}</response>")));

		ResponseEntity response = wireMockService.getDetails();

		logger.error("resposne is "+response);


		assertEquals(true,response.getBody().toString().contains("id"));


	}
}

