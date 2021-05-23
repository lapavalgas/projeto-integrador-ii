package com.usj.fastservice.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class DadosServicoDTO {

	private Long servico_id;
	
	private String nome;

	private String descricao;
	
	private String categoria;
	
	private String valor;
	
	private String statusOperante;
	
	private DadosUsuarioDTO usuarioProfissional;

	private String msg;
	
}