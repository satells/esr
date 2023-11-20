package com.esr.tipos.tiponotificador;

public enum NivelUrgencia {
	URGENTE(0), NORMAL(1);

	private int index;

	NivelUrgencia(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
