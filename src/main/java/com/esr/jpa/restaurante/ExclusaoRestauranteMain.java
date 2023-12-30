package com.esr.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.esr.EsrApplication;
import com.esr.domain.model.Restaurante;
import com.esr.domain.repository.RestauranteRepository;

public class ExclusaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(EsrApplication.class).web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository cadastroRepository = context.getBean(RestauranteRepository.class);

		Restaurante Restaurante = new Restaurante();
		Restaurante.setId(1L);
		Restaurante.setNome("austríacfdsfo");
		/*
		 * Quando é alteração basta passar a alteração com o id do registro que se
		 * deseja alterar e o método merge busca pelo id fornecido e altera o registro
		 * se o registro existe ele atualiza se não existir ele insere
		 */

		cadastroRepository.delete(Restaurante);
	}
}
