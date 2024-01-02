package com.esr.api.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.Random;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import com.esr.BaseTest;
import com.esr.domain.model.Cozinha;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CozinhaControllerTest extends BaseTest {

	@Test
	public void retorna_lista_de_cozinhas() throws Exception {
		RequestBuilder request = get("/cozinhas").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.*", hasSize(greaterThan(150))),
				content().contentType(APPLICATION_JSON));
	}

	@Test
	public void retorna_lista_de_cozinhas_xml() throws Exception {
		RequestBuilder request = get("/cozinhas").contentType(APPLICATION_XML_VALUE).accept(APPLICATION_XML_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), xpath("//cozinhas").exists(),
				content().contentType(APPLICATION_XML_VALUE));
	}

	@Test
	public Cozinha salvar_cozinha() throws JsonProcessingException, Exception {
		Cozinha cozinha = new Cozinha();
		String nome = "Paranaense" + new Random().nextInt() * 100;
		cozinha.setNome(nome);

		String jsonCozinha = objectMapper.writeValueAsString(cozinha);

		RequestBuilder request = post("/cozinhas").contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(jsonCozinha);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isCreated(), jsonPath("$.nome", Matchers.is(nome)),
				content().contentType(APPLICATION_JSON));

		return objectMapper.readValue(result.andReturn().getResponse().getContentAsByteArray(), Cozinha.class);
	}

	@Test
	public void get_cozinha_pelo_id() throws Exception {
		RequestBuilder request = get("/cozinhas/{id}", "150").contentType(APPLICATION_JSON).accept(APPLICATION_JSON);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(jsonPath("$.id", Matchers.is(150)), status().isOk(),
				content().contentType(APPLICATION_JSON));
	}

	@Test
	public void get_cozinha_pelo_id_not_found() throws Exception {
		RequestBuilder request = get("/cozinhas/{id}", "1000050").contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());
	}

	@Test
	public void put_atualizar_cozinha() throws Exception {
		Cozinha cozinha = new Cozinha();

		cozinha.setId(150L);
		cozinha.setNome("Atualiza" + new Random().nextInt());

		String jsonCozinha = objectMapper.writeValueAsString(cozinha);

		RequestBuilder request = put("/cozinhas/{id}", cozinha.getId()).contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON).content(jsonCozinha);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(jsonPath("$.id", is(150)), jsonPath("$.nome", is(cozinha.getNome())), status().isOk(),
				content().contentType(APPLICATION_JSON));

	}

	@Test
	public void put_atualizar_cozinha_not_found() throws Exception {
		Cozinha cozinha = new Cozinha();
		cozinha.setId(3000L);
		cozinha.setNome("fdfdsafadf");

		String jsonCozinha = objectMapper.writeValueAsString(cozinha);

		RequestBuilder request = put("/cozinhas/{id}", cozinha.getId()).accept(APPLICATION_JSON)
				.contentType(APPLICATION_JSON).content(jsonCozinha);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());
	}

	@Test
	public void delete_cozinha() throws Exception {
		Cozinha cozinha = salvar_cozinha();
		RequestBuilder request = delete("/cozinhas/{id}", cozinha.getId());

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().is(NO_CONTENT.value()));
	}

	@Test
	public void delete_cozinha_not_found() throws Exception {
		RequestBuilder request = delete("/cozinhas/{id}", 48974564);

		ResultActions result = mockMvc.perform(request);

		result.andExpect(status().isNotFound());

	}

	@Test
	public void buscaPorNome() throws Exception {
		RequestBuilder request = get("/cozinhas/por-nome").param("nome", "cano").accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.*", hasSize(greaterThan(1))),
				content().contentType(APPLICATION_JSON));
	}

}
