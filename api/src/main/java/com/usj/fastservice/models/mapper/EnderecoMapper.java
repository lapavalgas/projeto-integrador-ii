package com.usj.fastservice.models.mapper;

import com.usj.fastservice.models.Complemento;
import com.usj.fastservice.models.Endereco;
import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;

public class EnderecoMapper {

	public static EnderecoCompleto toModel(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		Endereco endereco = Endereco.builder()
				.cep(usuarioCadastroRequestDTO.getCep())
				.estado(usuarioCadastroRequestDTO.getEstado())
				.municipio(usuarioCadastroRequestDTO.getMunicipio())
				.bairro(usuarioCadastroRequestDTO.getBairro())
				.logradouro(usuarioCadastroRequestDTO.getLogradouro())
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
	
	public static EnderecoCompleto toReplace(DadosUsuarioDTO enderecoAtualizar, EnderecoCompleto enderecoCompleto) {
		enderecoCompleto.getEndereco().setCep(enderecoAtualizar.getCep());
		enderecoCompleto.getEndereco().setEstado(enderecoAtualizar.getEstado());
		enderecoCompleto.getEndereco().setMunicipio(enderecoAtualizar.getMunicipio());
		enderecoCompleto.getEndereco().setBairro(enderecoAtualizar.getBairro());
		enderecoCompleto.getEndereco().setLogradouro(enderecoAtualizar.getLogradouro());
		enderecoCompleto.getComplemento().setNumero(enderecoAtualizar.getNumero());
		enderecoCompleto.getComplemento().setComplemento(enderecoAtualizar.getComplemento());
		return enderecoCompleto;
	}
	
	
	

	
}
