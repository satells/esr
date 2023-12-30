package com.esr.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Cozinha;
import com.esr.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroRepository = context.getBean(CozinhaRepository.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("austríacfdsfo");
		System.out.println(cozinha);

		Cozinha cozinhaAdicionada = cadastroRepository.save(cozinha);
		System.out.println(cozinhaAdicionada);
	}
}
