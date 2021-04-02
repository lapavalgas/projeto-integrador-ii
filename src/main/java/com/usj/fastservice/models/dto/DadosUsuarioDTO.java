package com.usj.fastservice.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DadosUsuarioDTO {

	private String usuario_id;
	
	private String cpf;

	private String nome;

	private String dataNascimento;

	private String genero;

	private String email;

	private String telefone;

 	private String cep;
 	
	private String estado;
	
	private String municipio;
	
	private String bairro;

	private String logradouro;
	
	private String numero;

	private String complemento;
	
	private String msg;
 
}
