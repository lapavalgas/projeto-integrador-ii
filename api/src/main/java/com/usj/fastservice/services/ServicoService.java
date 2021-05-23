package com.usj.fastservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.dto.DadosServicoDTO;
import com.usj.fastservice.models.mapper.ServicoMapper;
import com.usj.fastservice.repositories.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public DadosServicoDTO cadastroDeServico(Long idUsuario, DadosServicoDTO servicoRequestDto) throws Exception {
		try {
			Servicos servico = ServicoMapper.toModel(servicoRequestDto);
			servico.setUsuario(usuarioService.readUsuarioRepositoryById(idUsuario));
			UsuarioService.isUsuarioAtivo(servico.getUsuario());
			servico = servicoRepository.save(servico);
			return ServicoMapper.toDto(servico);
		} catch (Exception e) {
			return ServicoMapper.setMsg(null, e.getMessage());
		}
	}
	
	public List<DadosServicoDTO> carregarServicos(Long idUsuario) {
		try {
			List<Servicos> listaServico = servicoRepository.findAllByUsuarioId(idUsuario);
			if (listaServico.isEmpty()) { throw new Exception("Algo de errado não está certo!!! :/ "); }
			UsuarioService.isUsuarioAtivo(listaServico.get(0).getUsuario());
			return ServicoMapper.toListDto(listaServico);
		} catch (Exception e) {
			List<DadosServicoDTO> listaServico = new ArrayList<DadosServicoDTO>();
			listaServico.add(ServicoMapper.setMsg(null, e.getMessage()));
			return listaServico;
		}
	}
	
	public DadosServicoDTO carregarDadosDoServico(Long idServico) throws Exception {
		try {
			Servicos servico = readServicoRepositoryById(idServico);
			UsuarioService.isUsuarioAtivo(servico.getUsuario());
			return ServicoMapper.toDto(servico);
		} catch (Exception e) {
			return ServicoMapper.setMsg(null, e.getMessage());
		}
	}
	
	public List<DadosServicoDTO> carregarServicos() {
		try {
			List<Servicos> listaServico = servicoRepository.findAll();
			return ServicoMapper.toListDto(listaServico);
		} catch (Exception e) {
			List<DadosServicoDTO> listaServico = new ArrayList<DadosServicoDTO>();
			listaServico.add(ServicoMapper.setMsg(null, "Erro inesperado, entre em contato com o administrador."));
			return listaServico;
		}
	}

	public DadosServicoDTO setStatusServicos(Long idUsuario, Long idServico, boolean stts) throws Exception {
		try {
			Servicos servico = readServicoRepositoryById(idServico);
			UsuarioService.isUsuarioAtivo(servico.getUsuario());			
			if (servico.getUsuario().getId().equals(idUsuario)) {
				servico.setStatusOperante(String.valueOf(stts));
				servicoRepository.save(servico);
				String valorStts = stts ? "ativado" : "desativado";
				return ServicoMapper.setMsg(idServico, "O servico foi " + valorStts);
			} else { 
				return ServicoMapper.setMsg(null, "Você não tem permissão para editar esse serviço ");
			}
		} catch (Exception e) {
			return ServicoMapper.setMsg(null, e.getMessage());
		}
	}
	
	Servicos readServicoRepositoryById(Long idServico) throws Exception {
		return servicoRepository.findById(idServico).orElseThrow(() -> new Exception("Servico não encontrado!"));
	}
	
	List<Servicos> readAllServicosRepository(Long idUsuario) throws Exception {
		return servicoRepository.findAllByUsuarioId(idUsuario); 
	}

}
