package com.esr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.esr.domain.model.Cliente;

class ClienteTest {
	NullPointerException exception;

	@Test
	void test_error_nome_null() {
		exception = assertThrows(NullPointerException.class, () -> new Cliente(null, "mail@mail.com", "11 9999-7777"));

		assertEquals("nome is marked non-null but is null", exception.getMessage());
	}

	@Test
	void test_error_email_null() {
		exception = assertThrows(NullPointerException.class, () -> new Cliente("Nome", null, "11 9999-7777"));

		assertEquals("email is marked non-null but is null", exception.getMessage());
	}

	@Test
	void test_error_telefone_null() {
		exception = assertThrows(NullPointerException.class, () -> new Cliente("Nome", "mail@mail.com", null));

		assertEquals("telefone is marked non-null but is null", exception.getMessage());
	}

	@Test
	void test_cliente_created() {
		Cliente cliente = new Cliente("Nome", "mail@mail.com", "11 9999-7777");

		assertThat(cliente, hasToString("Cliente(nome=Nome, email=mail@mail.com, telefone=11 9999-7777, ativo=false)"));

		cliente.ativar();

		assertThat(cliente, hasToString("Cliente(nome=Nome, email=mail@mail.com, telefone=11 9999-7777, ativo=true)"));

	}
}
