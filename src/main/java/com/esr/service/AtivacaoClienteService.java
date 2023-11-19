package com.esr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired
	@Qualifier("notificadoresSms")
	private List<Notificador> notificadores;

	public void ativar(Cliente cliente) {
		String mensagem = "Você foi ativado.";
		cliente.ativar();
		notificadores.forEach(notificador -> notificador.notificar(cliente, mensagem));
	}
}