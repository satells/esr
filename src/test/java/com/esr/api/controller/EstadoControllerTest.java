package com.esr.api.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import com.esr.BaseTest;
import com.esr.domain.model.Estado;

class EstadoControllerTest extends BaseTest {

	@Test
	void listar_estados() throws Exception {

		RequestBuilder request = get("/estados").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.*", hasSize(greaterThan(1))));
	}

	@Test
	void buscar_estado() throws Exception {
		RequestBuilder request = get("/estados/{id}", 1).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), content().contentType(APPLICATION_JSON_VALUE), jsonPath("$.id", is(1)));
	}

	@Test
	void buscar_estado_not_found() throws Exception {
		RequestBuilder request = get("/estados/{id}", 11111).contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());
	}

	@Test
	Estado salvar_estado() throws Exception {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do sul");

		String jsonEstado = objectMapper.writeValueAsString(estado);

		RequestBuilder request = post("/estados").content(jsonEstado).accept(APPLICATION_JSON_VALUE)
				.contentType(APPLICATION_JSON_VALUE);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isCreated(), content().contentType(APPLICATION_JSON_VALUE),
				jsonPath("$.nome", is(estado.getNome())));

		return objectMapper.readValue(result.andReturn().getResponse().getContentAsByteArray(), Estado.class);
	}

	@Test
	void alterar_estado() throws Exception {
		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Rio Grande do Sul" + new Random().nextInt());

		String jsonEstado = objectMapper.writeValueAsString(estado);

		RequestBuilder request = put("/estados").contentType(APPLICATION_JSON_VALUE).content(APPLICATION_JSON_VALUE)
				.content(jsonEstado);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isOk(), jsonPath("$.id", is(1)), jsonPath("$.nome", is(estado.getNome())),
				content().contentType(APPLICATION_JSON_VALUE));

	}

	@Test
	void alterar_estado_not_found() throws Exception {
		Estado estado = new Estado();
		estado.setId(456L);
		estado.setNome("Rio Grande do Sul" + new Random().nextInt());

		String jsonEstado = objectMapper.writeValueAsString(estado);

		RequestBuilder request = put("/estados").contentType(APPLICATION_JSON_VALUE).content(APPLICATION_JSON_VALUE)
				.content(jsonEstado);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound(),
				content().string(String.format("Estado não encontrado com o id %d", estado.getId())));

	}

	@Test
	void apagar_estado() throws Exception {
		Estado estado = salvar_estado();

		RequestBuilder request = delete("/estados/{id}", estado.getId());

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().is(NO_CONTENT.value()));
	}

	@Test
	void apagar_estado_not_found() throws Exception {

		RequestBuilder request = delete("/estados/{id}", 300);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().isNotFound());
	}

	@Test
	void apagar_estado_conflit() throws Exception {

		RequestBuilder request = delete("/estados/{id}", 1);

		ResultActions result = mockMvc.perform(request);

		result.andExpectAll(status().is(CONFLICT.value()));
	}

}
