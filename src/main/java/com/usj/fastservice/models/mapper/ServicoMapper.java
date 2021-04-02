package com.usj.fastservice.models.mapper;

import java.util.ArrayList;
import java.util.List;

import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.dto.DadosServicoDTO;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;

public class ServicoMapper {
	
	public static DadosServicoDTO setMsg(Long id, String msg) {
		return DadosServicoDTO.builder()
							.servico_id(String.valueOf(id))
							.msg(msg)
							.build();
	}

	public static DadosServicoDTO toServicoDTO(Servicos servico) {
		return DadosServicoDTO.builder()
				.servico_id(String.valueOf(servico.getId()))
				.nome(servico.getNome())
				.descricao(servico.getDescricao())
				.categoria(servico.getCategoria())
				.statusOperante(servico.getStatusOperante())
				.usuarioProfissional(UsuarioMapper.toUsuarioDTO(servico.getUsuario()))
				.build();
	}
	
	public static List<DadosServicoDTO> toListServicoDTO(List<Servicos> listaServico){
		List<DadosServicoDTO> listaServicosDto = new ArrayList<DadosServicoDTO>();
		for (Servicos servico : listaServico) { listaServicosDto.add(ServicoMapper.toServicoDTOOwner(servico)); }
		return listaServicosDto;
	}
	
	public static DadosServicoDTO toServicoDTOOwner(Servicos servico) {
		return DadosServicoDTO.builder()
				.servico_id(String.valueOf(servico.getId()))
				.nome(servico.getNome())
				.descricao(servico.getDescricao())
				.categoria(servico.getCategoria())
				.statusOperante(servico.getStatusOperante())
				.build();
	}
	
	public static Servicos toServicos(DadosServicoDTO dadosServicoDTO) {
		return Servicos.builder()
				.nome(dadosServicoDTO.getNome())
				.descricao(dadosServicoDTO.getDescricao())
				.categoria(dadosServicoDTO.getCategoria())
				.valor(dadosServicoDTO.getValor())
				.statusOperante(dadosServicoDTO.getStatusOperante())
				.usuario(UsuarioMapper.toUsuario(dadosServicoDTO.getUsuarioProfissional()))
				.build();
	}
	
	
}
