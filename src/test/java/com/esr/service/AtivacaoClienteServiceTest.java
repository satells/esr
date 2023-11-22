package com.esr.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.esr.BaseTest;
import com.esr.domain.model.Cliente;

class AtivacaoClienteServiceTest extends BaseTest {

	@Autowired
	AtivacaoClienteService service;

	@Test
	void test_notificar_cliente() {

		Cliente mauro = new Cliente("Mauro", "mauro@mail.com", "11 5878-9988");
		Cliente heitor = new Cliente("Heitor", "heitor@mail.com", "11 2254-9878");

		service.ativar(mauro);
		service.ativar(heitor);
	}
}