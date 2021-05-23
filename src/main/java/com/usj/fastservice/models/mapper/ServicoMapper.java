package com.usj.fastservice.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.dto.DadosServicoDTO;

@Component
public class ServicoMapper {
	
	public static DadosServicoDTO setMsg(Long id, String msg) {
		return DadosServicoDTO.builder()
							.servico_id(id)
							.msg(msg)
							.build();
	}

	public static DadosServicoDTO toDto(Servicos servico) {
		return DadosServicoDTO.builder()
				.servico_id(servico.getId())
				.nome(servico.getNome())
				.descricao(servico.getDescricao())
				.categoria(servico.getCategoria())
				.valor(servico.getValor())
				.statusOperante(servico.getStatusOperante())
				.usuarioProfissional(UsuarioMapper.toLoggedUsuarioDTO(servico.getUsuario()))
				.build();
	}
	
	public static Servicos toModel(DadosServicoDTO dadosServicoDTO) {
		return Servicos.builder()
				.nome(dadosServicoDTO.getNome())
				.descricao(dadosServicoDTO.getDescricao())
				.categoria(dadosServicoDTO.getCategoria())
				.valor(dadosServicoDTO.getValor())
				.statusOperante(dadosServicoDTO.getStatusOperante())
				.usuario(UsuarioMapper.toModel(dadosServicoDTO.getUsuarioProfissional()))
				.build();
	}
	
	public static List<DadosServicoDTO> toListDto(List<Servicos> listaServico){
		List<DadosServicoDTO> listaServicosDto = new ArrayList<DadosServicoDTO>();
		for (Servicos servico : listaServico) { listaServicosDto.add(ServicoMapper.toDto(servico)); }
		return listaServicosDto;
	}
	
}
