package com.esr.api.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.esr.BaseTest;
import com.esr.domain.model.Cozinha;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CozinhaControllerTest extends BaseTest {

	@Test
	public void retorna_lista_de_cozinhas() throws Exception {
		mockMvc.perform(get("/cozinhas").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE)

		).andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(greaterThan(150))));
	}

	@Test
	public void retorna_lista_de_cozinhas_xml() throws Exception {
		mockMvc.perform(get("/cozinhas").contentType(APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE)

		).andExpect(status().isOk()).andExpect(xpath("//cozinhas").exists());
	}

	@Test
	public void salvar_cozinha() throws JsonProcessingException, Exception {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Paranaense" + new Random().nextInt() * 100);

		String jsonCozinha = new ObjectMapper().writeValueAsString(cozinha);

		mockMvc.perform(post("/cozinhas").contentType(APPLICATION_JSON).accept(APPLICATION_JSON).content(jsonCozinha))
				.andExpect(status().isCreated());
	}

	@Test
	public void get_cozinha_pelo_id() throws Exception {
		mockMvc.perform(get("/cozinha/", "150").contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
