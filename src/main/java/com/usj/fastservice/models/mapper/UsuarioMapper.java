package com.usj.fastservice.models.mapper;

import com.usj.fastservice.models.Complemento;
import com.usj.fastservice.models.Contato;
import com.usj.fastservice.models.Endereco;
import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;

public class UsuarioMapper {
	
	
	public static DadosUsuarioDTO setMsg(Long id, String msg) {
		return DadosUsuarioDTO.builder()
							.usuario_id(String.valueOf(id))
							.msg(msg)
							.build();
	}

	/**
	 */

	public static Usuario toUsuario(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		Contato contato = Contato.builder()
							.email(usuarioCadastroRequestDTO.getEmail())
							.build();
		Endereco endereco = Endereco.builder()
							.cep(usuarioCadastroRequestDTO.getCep())
							.build();
		Complemento complemento = Complemento.builder()
							.numero(usuarioCadastroRequestDTO.getNumero())
							.complemento(usuarioCadastroRequestDTO.getComplemento())
							.build();
		EnderecoCompleto enderecoCompleto = EnderecoCompleto.builder()
							.endereco(endereco)
							.complemento(complemento)
							.build();
		return Usuario.builder()
							.cpf(usuarioCadastroRequestDTO.getCpf())
							.nome(usuarioCadastroRequestDTO.getNome())
							.dataNascimento(usuarioCadastroRequestDTO.getDataNascimento())
							.genero(usuarioCadastroRequestDTO.getGenero())
							.contato(contato)
							.enderecoCompleto(enderecoCompleto)
							.build();
	}
	
	public static DadosUsuarioDTO toUsuarioDTO(Usuario usuario) {
		return DadosUsuarioDTO.builder()
							.usuario_id(String.valueOf(usuario.getId()))
							.nome(usuario.getNome())
							.build();
	}

	public static DadosUsuarioDTO toUsuarioDTO(Contato contato) {
		return DadosUsuarioDTO.builder()
							.usuario_id(String.valueOf(contato.getId()))
							.email(contato.getEmail())
							.telefone(contato.getTelefone())
							.build();
	}
	
	public static Contato toContato(DadosUsuarioDTO usuarioDTO) {
		return Contato.builder()
							.id(Long.decode(usuarioDTO.getUsuario_id()))
							.email(usuarioDTO.getEmail())
							.telefone(usuarioDTO.getTelefone())
							.build();
	}
	
	public static DadosUsuarioDTO toEndereco(EnderecoCompleto enderecoCompleto) {
		return DadosUsuarioDTO.builder()
							.usuario_id(String.valueOf(enderecoCompleto.getId()))
							.cep(enderecoCompleto.getEndereco().getCep())
							.estado(enderecoCompleto.getEndereco().getEstado())
							.municipio(enderecoCompleto.getEndereco().getMunicipio())
							.bairro(enderecoCompleto.getEndereco().getBairro())
							.logradouro(enderecoCompleto.getEndereco().getLogradouro())
							.numero(enderecoCompleto.getComplemento().getNumero())
							.complemento(enderecoCompleto.getComplemento().getComplemento())
							.build();
	}
	

}
