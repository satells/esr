package com.esr.service;

import static java.lang.String.format;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esr.domain.model.Cliente;
import com.esr.domain.model.Produto;
import com.esr.notificacao.Notificador;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@Component
public class EmissaoNotaFiscalService {

	private final List<Notificador> notificadores;

	@Autowired
	public EmissaoNotaFiscalService(@TipoNotificador(NivelUrgencia.URGENTE) final List<Notificador> notificadores) {
		this.notificadores = notificadores;
	}

	public void emitir(Cliente cliente, Produto produto) {
		// TODO emite nota fiscal aqui...

		String mensagem = format("Nota Fiscal do %s foi emitida", produto.getNome());

		notificadores.forEach(notificador -> notificador.notificar(cliente, mensagem));
	}
}