package com.esr.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Cozinha;
import com.esr.domain.repository.CozinhaRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroRepository = context.getBean(CozinhaRepository.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setId(150000L);
		cozinha.setNome("austríacfdsfo");
		/*
		 * Quando é alteração basta passar a alteração com o id do registro que se
		 * deseja alterar e o método merge busca pelo id fornecido e altera o registro
		 * se o registro existe ele atualiza se não existir ele insere
		 */

		Cozinha alterada = cadastroRepository.salvar(cozinha);
		System.out.println(alterada);
	}
}
