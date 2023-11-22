package com.esr.domain.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public class Produto {

	@NonNull
	private String nome;
	@NonNull
	private BigDecimal valorTotal;

}
