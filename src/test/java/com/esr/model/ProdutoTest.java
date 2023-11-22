package com.esr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.esr.domain.model.Produto;

class ProdutoTest {

	NullPointerException exception;

	@Test
	void test_error_nome_null() {
		exception = assertThrows(NullPointerException.class, () -> new Produto(null, new BigDecimal(15.01)));

		assertEquals("nome is marked non-null but is null", exception.getMessage());
	}

	@Test
	void test_error_valorTotal_null() {
		exception = assertThrows(NullPointerException.class, () -> new Produto("Nome", null));

		assertEquals("valorTotal is marked non-null but is null", exception.getMessage());
	}

	@Test
	void test_produto_created() {
		Produto produto = new Produto("Nome", new BigDecimal("155.99"));

		assertThat(produto, hasToString("Produto(nome=Nome, valorTotal=155.99)"));

	}
}
