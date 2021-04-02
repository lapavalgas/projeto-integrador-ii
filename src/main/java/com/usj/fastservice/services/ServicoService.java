package com.usj.fastservice.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosServicoDTO;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.models.mapper.ServicoMapper;
import com.usj.fastservice.repositories.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public DadosServicoDTO cadastroDeServico(Long idUsuario, DadosServicoDTO servicoRequestDto) throws Exception {
		Servicos servico = ServicoMapper.toServicos(servicoRequestDto);
		servico.setUsuario(usuarioService.readUsuarioRepositoryById(idUsuario));
		servico = servicoRepository.save(servico);
		return ServicoMapper.toServicoDTO(servico);
	}
	
	public List<DadosServicoDTO> carregarServicos(Long idUsuario) {
		List<Servicos> listaServico = servicoRepository.findAllByUsuarioId(idUsuario);
		return ServicoMapper.toListServicoDTO(listaServico);
	}
	
	public DadosServicoDTO carregarDadosDoServico(Long idServico) throws Exception {
		Servicos servico = servicoRepository.findById(idServico).orElseThrow(() -> new Exception("Servico não encontrado!"));
		return ServicoMapper.toServicoDTO(servico);
	}
	
	public List<DadosServicoDTO> carregarServicos() {
		List<Servicos> listaServico = servicoRepository.findAll();
		return ServicoMapper.toListServicoDTO(listaServico);
	}

	public DadosServicoDTO setStatusServicos(Long idUsuario, Long idServico, String stts) throws Exception {
		Servicos servico = servicoRepository.findById(idServico).orElseThrow(() -> new Exception("Servico não encontrado!"));
		if (servico.getUsuario().getId() == idUsuario) {
			servico.setStatusOperante(stts);
			servicoRepository.save(servico);
			return ServicoMapper.setMsg(idServico, "O status do servico foi alterado para " + stts);
		} else { 
			return ServicoMapper.setMsg(idServico, "Você não tem permissão para editar esse serviço ");
		}
	}

}
