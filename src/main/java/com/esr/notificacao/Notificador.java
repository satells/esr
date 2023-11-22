package com.esr.notificacao;

import com.esr.domain.model.Cliente;

public interface Notificador {
	public void notificar(Cliente cliente, String mensagem);
}
