package com.esr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.service.events.publishers.ClienteAtivadoEvent;

@Component
public class AtivacaoClienteService {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();

		this.eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}
}