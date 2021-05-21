package com.usj.fastservice.models.mapper;

import org.springframework.stereotype.Component;

import com.usj.fastservice.models.Contato;
import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;

@Component
public class UsuarioMapper {

	public static DadosUsuarioDTO setMsg(Long id, String msg) {
		return DadosUsuarioDTO.builder().usuario_id(id).msg(msg).build();
	}

	public static Usuario toModel(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		return Usuario.builder()
				.id(usuarioCadastroRequestDTO.getUsuario_id())
				.cpf(usuarioCadastroRequestDTO.getCpf())
				.nome(usuarioCadastroRequestDTO.getNome())
				.dataNascimento(usuarioCadastroRequestDTO.getDataNascimento())
				.genero(usuarioCadastroRequestDTO.getGenero())
				.build();
	}

	public static DadosUsuarioDTO toDto(Usuario usuario) {
		return DadosUsuarioDTO.builder()
				.usuario_id(usuario.getId())
				.nome(usuario.getNome())
				.cpf(usuario.getCpf())
				.dataNascimento(usuario.getDataNascimento())
				.genero(usuario.getGenero())
				.email(usuario.getContato().getEmail())
				.telefone(usuario.getContato().getTelefone())
				.cep(usuario.getEnderecoCompleto().getEndereco().getCep())
				.estado(usuario.getEnderecoCompleto().getEndereco().getEstado())
				.municipio(usuario.getEnderecoCompleto().getEndereco().getMunicipio())
				.bairro(usuario.getEnderecoCompleto().getEndereco().getBairro())
				.logradouro(usuario.getEnderecoCompleto().getEndereco().getLogradouro())
				.numero(usuario.getEnderecoCompleto().getComplemento().getNumero())
				.complemento(usuario.getEnderecoCompleto().getComplemento().getComplemento())
				.build();
	}

	/**
	 */

	public static DadosUsuarioDTO toLoggedUsuarioDTO(Usuario usuario) {
		return DadosUsuarioDTO.builder().usuario_id(usuario.getId()).nome(usuario.getNome()).build();
	}

	public static DadosUsuarioDTO toUsuarioDTO(Contato contato) {
		return DadosUsuarioDTO.builder().usuario_id(contato.getId()).email(contato.getEmail())
				.telefone(contato.getTelefone()).build();
	}

	public static Contato toContato(DadosUsuarioDTO usuarioDTO) {
		return Contato.builder().id(usuarioDTO.getUsuario_id()).email(usuarioDTO.getEmail())
				.telefone(usuarioDTO.getTelefone()).build();
	}

	public static DadosUsuarioDTO toEndereco(EnderecoCompleto enderecoCompleto) {
		return DadosUsuarioDTO.builder().usuario_id(enderecoCompleto.getId())
				.cep(enderecoCompleto.getEndereco().getCep()).estado(enderecoCompleto.getEndereco().getEstado())
				.municipio(enderecoCompleto.getEndereco().getMunicipio())
				.bairro(enderecoCompleto.getEndereco().getBairro())
				.logradouro(enderecoCompleto.getEndereco().getLogradouro())
				.numero(enderecoCompleto.getComplemento().getNumero())
				.complemento(enderecoCompleto.getComplemento().getComplemento()).build();
	}

}
