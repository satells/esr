package com.esr.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Minha Cozinha");
		System.out.println(cozinha);

		Cozinha cozinhaAdicionada = cadastroCozinha.adicionar(cozinha);
		System.out.println(cozinhaAdicionada);
	}
}
