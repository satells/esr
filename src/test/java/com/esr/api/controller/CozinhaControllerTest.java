package com.esr.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.esr.domain.model.Cozinha;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = ApplicationArguments.class)
@AutoConfigureMockMvc
public class CozinhaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CozinhaController cozinhaController;

	@Test
	public void deveRetornarUmaListaDeCozinhasTest() throws Exception {
		List<Cozinha> cozinhas = new ArrayList<>();

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Tailandesa");

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setId(2L);
		cozinha2.setNome("Indiana");

		cozinhas.add(cozinha);
		cozinhas.add(cozinha2);

//		BDDMockito.given(cozinhaController.listar()).willReturn(cozinhas);

		String json = new ObjectMapper().writeValueAsString(cozinhas);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/cozinhas")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(jsonPath("$", Matchers.hasSize(2))).andExpect(status().isOk());
	}

}
