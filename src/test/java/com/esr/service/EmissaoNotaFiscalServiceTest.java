package com.esr.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.esr.BaseTest;
import com.esr.model.Cliente;
import com.esr.model.Produto;

class EmissaoNotaFiscalServiceTest extends BaseTest {

	@Autowired
	EmissaoNotaFiscalService service = new EmissaoNotaFiscalService();

	@Test
	void test_emissao_nota_fiscal() {

		Cliente mauro = new Cliente("Mauro", "mauro@mail.com", "11 9988-4587");
		Produto camisa = new Produto("Camisa Social Azul Turquesa", new BigDecimal("150.88"));

		service.emitir(mauro, camisa);
	}
}