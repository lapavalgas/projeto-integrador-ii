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
public class DadosUsuarioDTO {

	private Long usuario_id;

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

	public void removeCaracteresEspeciaisCpf() {
		setCpf(this.cpf.replace("*", "").replace("!", "").replace("@", "").replace("#", "").replace("$", "")
				.replace("%", "").replace("&", "").replace("*", "").replace("'", "").replace("\"", "").replace("=", "")
				.replace("+", "").replace(".", "").replace("-", "").replace("_", ""));
	}

}
