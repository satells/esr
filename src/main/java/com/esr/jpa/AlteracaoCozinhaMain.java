package com.esr.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Cozinha;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setId(150000L);
		cozinha.setNome("austríacfdsfo");
		/*
		 * Quando é alteração basta passar a alteração com o id do registro que se
		 * deseja alterar e o método merge busca pelo id fornecido e altera o registro
		 * se o registro existe ele atualiza se não existir ele insere
		 */

		Cozinha alterada = cadastroCozinha.salvar(cozinha);
		System.out.println(alterada);
	}
}
