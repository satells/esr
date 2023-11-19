package com.esr.service;

import static java.lang.String.format;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.model.Produto;
import com.esr.notificacao.Notificador;

@Component
public class EmissaoNotaFiscalService {

	@Autowired
	@Qualifier("notificadoresEmailSms")
	private List<Notificador> notificadores;

	public void emitir(Cliente cliente, Produto produto) {
		// TODO emite nota fiscal aqui...

		String mensagem = format("Nota Fiscal do %s foi emitida", produto.getNome());

		notificadores.forEach(notificador -> notificador.notificar(cliente, mensagem));
	}
}