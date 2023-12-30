package com.esr.api.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.esr.BaseTest;
import com.esr.domain.model.Cozinha;
import com.esr.domain.model.Restaurante;

class RestauranteControllerTest extends BaseTest {

	@Test
	void listar_restaurantes() throws Exception {
		RequestBuilder request = get("/restaurantes").contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.*", hasSize(greaterThan(30))),
				content().contentType(APPLICATION_JSON_VALUE));
	}

	@Test
	void buscar_por_id() throws Exception {
		RequestBuilder request = get("/restaurantes/{id}", 1).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.id", is(1)), content().contentType(APPLICATION_JSON_VALUE));
	}

	@Test
	void buscar_por_id_not_found() throws Exception {
		RequestBuilder request = get("/restaurantes/{id}", 4564564).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());

	}

	@Test
	void adicionar_restaurante() throws Exception {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Restaurante Paulista");
		restaurante.setTaxaFrete(new BigDecimal("15.15"));

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		restaurante.setCozinha(cozinha);

		String jsonRestaurante = objectMapper.writeValueAsString(restaurante);

		RequestBuilder request = post("/restaurantes").contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(jsonRestaurante);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isCreated(), jsonPath("$.nome", is("Restaurante Paulista")),
				jsonPath("$.taxaFrete", is(15.15)), jsonPath("$.cozinha.id", is(1)),
				content().contentType(APPLICATION_JSON_VALUE));

	}

	@Test
	void adicionar_restaurante_bad_request() throws Exception {

		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Restaurante Paulista");
		restaurante.setTaxaFrete(new BigDecimal("15.15"));

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1000L);
		restaurante.setCozinha(cozinha);

		String jsonRestaurante = objectMapper.writeValueAsString(restaurante);

		RequestBuilder request = post("/restaurantes").contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(jsonRestaurante);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isBadRequest(),
				MockMvcResultMatchers.content().string("Não existe cozinha com o id 1000"));

	}

	@Test
	void alterar_restaurante() throws Exception {
		Restaurante restaurante = new Restaurante();

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);

		restaurante.setId(3L);
		restaurante.setNome("Alterado" + new Random().nextInt());
		restaurante.setTaxaFrete(new BigDecimal("19.99"));
		restaurante.setCozinha(cozinha);

		String jsonRestaurante = objectMapper.writeValueAsString(restaurante);

		RequestBuilder request = put("/restaurantes/{id}", restaurante.getId()).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(jsonRestaurante);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), content().contentType(APPLICATION_JSON_VALUE), jsonPath("$.id", is(3))

				, jsonPath("$.nome", is(restaurante.getNome()))

				, jsonPath("$.cozinha.id", is(1))

		);

	}

	@Test
	void alterar_restaurante_not_found() throws Exception {
		Restaurante restaurante = new Restaurante();

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);

		restaurante.setId(7000L);
		restaurante.setNome("Alterado" + new Random().nextInt());
		restaurante.setTaxaFrete(new BigDecimal("19.99"));
		restaurante.setCozinha(cozinha);

		String jsonRestaurante = objectMapper.writeValueAsString(restaurante);

		RequestBuilder request = put("/restaurantes/{id}", restaurante.getId()).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(jsonRestaurante);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());

	}

	@Test
	void alterar_restaurante_cozinha_not_found() throws Exception {
		Restaurante restaurante = new Restaurante();

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);

		restaurante.setId(7000L);
		restaurante.setNome("Alterado" + new Random().nextInt());
		restaurante.setTaxaFrete(new BigDecimal("19.99"));
		restaurante.setCozinha(cozinha);

		String jsonRestaurante = objectMapper.writeValueAsString(restaurante);

		RequestBuilder request = put("/restaurantes/{id}", restaurante.getId()).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(jsonRestaurante);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());
	}

	@Test
	void alterar_parcialmente() throws Exception {
		Restaurante restaurante = new Restaurante();

		restaurante.setId(1L);
		restaurante.setTaxaFrete(new BigDecimal("99.99"));
		Cozinha cozinha = new Cozinha();
//		cozinha.setId(1L);

		restaurante.setCozinha(cozinha);

		String jsonRestaurante = objectMapper.writeValueAsString(restaurante);

		RequestBuilder request = patch("/restaurantes/{id}", 1).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(jsonRestaurante);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(MockMvcResultMatchers.status().isOk());

	}

}