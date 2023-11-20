package com.esr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.notificacao.Notificador;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@Component
public class AtivacaoClienteService {

	private final List<Notificador> notificadores;

	@Autowired
	public AtivacaoClienteService(@TipoNotificador(NivelUrgencia.NORMAL) final List<Notificador> notificadores) {
		this.notificadores = notificadores;

	}

	public void ativar(Cliente cliente) {
		String mensagem = "Você foi ativado.";
		cliente.ativar();
		notificadores.forEach(notificador -> notificador.notificar(cliente, mensagem));
	}
}