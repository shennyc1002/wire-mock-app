package com.motel666.usereventservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motel666.usereventservice.entity.Event;
import com.motel666.usereventservice.repository.UserEventRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserEventServiceApplicationTests {


	Logger logger = LoggerFactory.getLogger("ProductSearchServiceApplicationTests");
	@Autowired
	MockMvc mockMvc;

	@Mock
	UserEventRepository userEventRepository;

	private ObjectMapper mapper = new ObjectMapper();
	@Test
	public void contextLoads() {
	}


	@Test
	public void testSearchByUserId() throws Exception
	{
		Event expected = new Event();



		String response = mockMvc.perform(get("/events/userid/1100"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Event> result = mapper.readValue(response,new TypeReference<List<Event>>(){});

		Assert.assertEquals("Size of the list ", 2, result.size());
	}

	@Test
	public void testSearchByUserIdData() throws Exception
	{

		String response = mockMvc.perform(get("/events/userid/1100"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Event> result = mapper.readValue(response,new TypeReference<List<Event>>(){});

		Assert.assertEquals( "free-booking", result.get(0).getEventType());
	}

	@Test
	public void testSearchByDate() throws Exception
	{

		String response = mockMvc.perform(get("/events/getbydate/2018-12-05/2018-12-15"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Event> result = mapper.readValue(response,new TypeReference<List<Event>>(){});

		Assert.assertNotNull(result);

	}

	@Test
	public void testSearchByDateNoData() throws Exception
	{

		String response = mockMvc.perform(get("/events/getbydate/2018-12-05/2018-12-10"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		logger.error("Result is "+response);

		List<Event> result = mapper.readValue(response,new TypeReference<List<Event>>(){});

		Assert.assertEquals(0,result.size());

	}
}

