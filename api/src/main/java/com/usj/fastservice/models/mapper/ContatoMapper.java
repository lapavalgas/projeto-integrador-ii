package com.usj.fastservice.models.mapper;

import com.usj.fastservice.models.Contato;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;

public class ContatoMapper {

	public static Contato toContato(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		return Contato.builder()
				.id(usuarioCadastroRequestDTO.getUsuario_id())
				.email(usuarioCadastroRequestDTO.getEmail())
				.telefone(usuarioCadastroRequestDTO.getTelefone())
				.build();
	}

}
