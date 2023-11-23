package com.esr.domain.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Data
public class Produto {

	@NonNull
	private String nome;
	@NonNull
	private BigDecimal valorTotal;

}
