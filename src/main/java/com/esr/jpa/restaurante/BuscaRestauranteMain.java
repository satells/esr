package com.esr.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Restaurante;
import com.esr.domain.repository.RestauranteRepository;

@lombok.Generated
public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository cadastroRepository = context.getBean(RestauranteRepository.class);
		Restaurante Restaurante = cadastroRepository.findById(150L).get();
		System.out.println(Restaurante.toString());

	}

}
