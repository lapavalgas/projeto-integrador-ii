package com.usj.fastservice.models.mapper;

import com.usj.fastservice.models.Complemento;
import com.usj.fastservice.models.Endereco;
import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;

public class EnderecoMapper {

	public static EnderecoCompleto toEnderecoLessId(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		Endereco endereco = Endereco.builder()
				.cep(usuarioCadastroRequestDTO.getCep())
				.estado(usuarioCadastroRequestDTO.getEstado())
				.municipio(usuarioCadastroRequestDTO.getMunicipio())
				.bairro(usuarioCadastroRequestDTO.getBairro())
				.logradouro(usuarioCadastroRequestDTO.getBairro())
				.build();
		Complemento complemento = Complemento.builder()
				.numero(usuarioCadastroRequestDTO.getNumero())
				.complemento(usuarioCadastroRequestDTO.getComplemento())
				.build();
		return EnderecoCompleto.builder()
				.id(usuarioCadastroRequestDTO.getUsuario_id())
				.endereco(endereco)
				.complemento(complemento)
				.build();
	}


	
}
