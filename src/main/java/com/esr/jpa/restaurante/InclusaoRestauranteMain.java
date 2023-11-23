package com.esr.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Restaurante;
import com.esr.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository cadastroRepository = context.getBean(RestauranteRepository.class);

		Restaurante Restaurante = new Restaurante();
		Restaurante.setNome("austríacfdsfo");
		System.out.println(Restaurante);

		Restaurante RestauranteAdicionada = cadastroRepository.salvar(Restaurante);
		System.out.println(RestauranteAdicionada);
	}
}
