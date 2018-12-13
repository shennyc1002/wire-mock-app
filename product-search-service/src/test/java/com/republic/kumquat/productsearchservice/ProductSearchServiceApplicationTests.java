package com.republic.kumquat.productsearchservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.republic.kumquat.productsearchservice.entity.Product;
import com.republic.kumquat.productsearchservice.service.ProductSearchImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductSearchServiceApplicationTests {


	Logger logger = LoggerFactory.getLogger("ProductSearchServiceApplicationTests");
	@Autowired
	MockMvc mockMvc;

	@Mock
	ProductSearchImpl productSearchService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSearchByBrandSize() throws Exception
	{
		Product expected = new Product();
		expected.setBrand("Tomy");


		String response = mockMvc.perform(get("/search/brand/Tomy"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Product> result = mapper.readValue(response,new TypeReference<List<Product>>(){});

		Assert.assertEquals("Size of the list ", 1, result.size());
	}


	@Test
	public void testSearchByBrandName() throws Exception
	{
		Product expected = new Product();
		expected.setBrand("Tomy");


		String response = mockMvc.perform(get("/search/brand/Tomy"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Product> result = mapper.readValue(response,new TypeReference<List<Product>>(){});

		Assert.assertEquals("Name returned from the list ", "Tomy", result.get(0).getBrand());
	}

	@Test
	public void testSearchByColorSize() throws Exception
	{
		Product expected = new Product();
		expected.setBrand("Tomy");


		String response = mockMvc.perform(get("/search/color/Red"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Product> result = mapper.readValue(response,new TypeReference<List<Product>>(){});

		Assert.assertEquals("Size of the list ", 1, result.size());
	}

	@Test
	public void testSearchByColor() throws Exception
	{
		Product expected = new Product();
		expected.setBrand("Tomy");


		String response = mockMvc.perform(get("/search/color/Red"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Product> result = mapper.readValue(response,new TypeReference<List<Product>>(){});

		Assert.assertEquals("Name returned from the list ", "Red", result.get(0).getColor());
	}
}

