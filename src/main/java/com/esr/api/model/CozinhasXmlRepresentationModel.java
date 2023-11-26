package com.esr.api.model;

import java.util.List;

import com.esr.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@Data // Gera construtores
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlRepresentationModel {

	@NonNull // indica que u7ma propriedade é obrigatória
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;
}
