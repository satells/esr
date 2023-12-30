package com.esr.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Cozinha;
import com.esr.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroRepository = context.getBean(CozinhaRepository.class);
		Cozinha cozinha = cadastroRepository.findById(150L).get();
		System.out.println(cozinha.toString());

	}

}
