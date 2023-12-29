package com.esr.api.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import com.esr.BaseTest;

class EstadoControllerTest extends BaseTest {

	@Test
	void test() throws Exception {

		RequestBuilder request = get("/estados").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.*", hasSize(greaterThan(1))));

	}

}
