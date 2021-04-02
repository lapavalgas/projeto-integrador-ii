package com.usj.fastservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DadosServicoDTO {

	private String servico_id;
	
	private String nome;

	private String descricao;
	
	private String categoria;
	
	private String valor;
	
	private String statusOperante;
	
	private DadosUsuarioDTO usuarioProfissional;

	private String msg;
	
}