package com.esr.infrastructure.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.esr.BaseTest;

class StartControllerTest extends BaseTest {

	@Test
	void test() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("iniciado")));
	}
}