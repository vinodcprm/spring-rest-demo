package com.vkp.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testEchoWithValue() throws Exception {
		String message = "Vinodkumar";
		String expectedResult ="Hello Vinodkumar";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/echo").param("message", message))
			.andExpect(status().isOk())
			.andReturn();	
		
		assertEquals(mvcResult.getResponse().getContentAsString(), expectedResult);
	}
	
	@Test
	public void testEchoWithValueNumber() throws Exception {
		String message = "123456";
		String expectedResult ="Hello 123456";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/echo").param("message", message))
			.andExpect(status().isOk())
			.andReturn();	
		
		assertEquals(mvcResult.getResponse().getContentAsString(), expectedResult);
	}
	
	@Test
	public void testEchoWithValueSpecialChars() throws Exception {
		String message = "@123#$%";
		String expectedResult ="Hello @123#$%";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/echo").param("message", message))
			.andExpect(status().isOk())
			.andReturn();		
		
		assertEquals(mvcResult.getResponse().getContentAsString(), expectedResult);
	}
	
	@Test
	public void testEchoWithOutValue() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/echo"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testHealthFormatBlank() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/health"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testHealthFormatShortLower() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/health").param("format", "short"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("OK"))
		.andExpect(jsonPath("$.currentTime").doesNotExist());
		
	}
	
	@Test
	public void testHealthFormatShortMixed()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/health").param("format", "SHort"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testHealthFormatLongLower()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/health").param("format", "long"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("OK"))
		.andExpect(jsonPath("$.currentTime").exists());
	}
	
	@Test
	public void testHealthFormatLongMixed()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/health").param("format", "LonG"))
		.andExpect(status().isBadRequest());
	}
}
