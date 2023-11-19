package com.esr.notificacao;

import com.esr.model.Cliente;

public interface Notificador {
	public void notificar(Cliente cliente, String mensagem);
}
