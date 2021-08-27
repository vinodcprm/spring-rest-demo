package com.vkp.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testEchoWithValue() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/echo").param("message", "Vinodkumar"))
			.andExpect(status().isOk());		
	}
	
	@Test
	public void testEchoWithValueNumber() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/echo").param("message", "123456"))
			.andExpect(status().isOk());		
	}
	
	@Test
	public void testEchoWithValueSpecialChars() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/echo").param("message", "@123#$%"))
			.andExpect(status().isOk());		
	}
	
	@Test
	public void testEchoWithOutValue() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/echo"))
		.andExpect(status().isBadRequest());
	}
}
