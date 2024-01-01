package com.esr.jpa.restaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Restaurante;
import com.esr.domain.repository.RestauranteRepository;

@lombok.Generated
public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository cadastroRepository = context.getBean(RestauranteRepository.class);
		List<Restaurante> Restaurantes = cadastroRepository.findAll();

		Restaurantes.forEach(Restaurante -> System.out.println(Restaurante.toString()));

	}

}
