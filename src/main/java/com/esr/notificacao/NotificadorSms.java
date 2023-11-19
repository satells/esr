package com.esr.notificacao;

import org.springframework.stereotype.Component;

import com.esr.model.Cliente;

@Component
public class NotificadorSms implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s\n", cliente.getNome(),
				cliente.getTelefone(), mensagem);
	}

}
