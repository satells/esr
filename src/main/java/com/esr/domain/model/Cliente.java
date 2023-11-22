package com.esr.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString

public class Cliente {

	@NonNull
	private String nome;
	@NonNull
	private String email;
	@NonNull
	private String telefone;

	private boolean ativo = false;

	public void ativar() {
		this.ativo = true;
	}
}
