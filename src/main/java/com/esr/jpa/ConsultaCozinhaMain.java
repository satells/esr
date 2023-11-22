package com.esr.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Cozinha;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);
		List<Cozinha> cozinhas = cadastroCozinha.listar();

		cozinhas.forEach(cozinha -> System.out.println(cozinha.toString()));

	}
}
