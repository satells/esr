package com.esr.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha c", Cozinha.class).getResultList();
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		/**
		 * 
		 * Coloca, incorpora a entidade <Cozinha> dentro do contexto de Persistencia.
		 * 
		 */

		// Quando é passado uma instância não há nenhum id atribuido na instância
		// Cozinha.
		// Este id é atribuido na instância de retorno do método merge.
		return manager.merge(cozinha);
	}

	public Cozinha busca(Long id) {
		return manager.find(Cozinha.class, id);

	}

	@Transactional
	public void remover(Cozinha cozinha) {
		Cozinha busca = this.busca(cozinha.getId());
		manager.remove(busca);

	}
}
