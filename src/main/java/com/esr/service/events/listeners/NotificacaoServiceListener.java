package com.esr.service.events.listeners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.esr.notificacao.Notificador;
import com.esr.service.events.publishers.ClienteAtivadoEvent;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@Component
public class NotificacaoServiceListener {

	private final List<Notificador> notificadores;

	@Autowired
	public NotificacaoServiceListener(@TipoNotificador(NivelUrgencia.NORMAL) final List<Notificador> notificadores) {
		this.notificadores = notificadores;

	}

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		String mensagem = "Seu cadastro no sistema foi ativado.";

		notificadores.forEach(notificador -> notificador.notificar(event.getCliente(), mensagem));
	}
}
