package com.esr.service.events.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.service.events.publishers.ClienteAtivadoEvent;

@Component
public class EmitirNotaFiscalServiceListener {

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		Cliente cliente = event.getCliente();

		String mensagem = String.format("Prezado %s, sua Nota Fiscal foi emtida.", cliente.toString());

		System.out.println(mensagem);
	}
}
