package com.esr.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString
@JsonRootName("cozinha")
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @JsonProperty("titulo")
	@Column(unique = true, nullable = false)
	private String nome;
//
//	@JsonIgnore
//	private String pais;

	@JsonIgnore /* Colocar para evitar serialização com */
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes;
}
