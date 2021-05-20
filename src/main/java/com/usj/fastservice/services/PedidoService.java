package com.usj.fastservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.DisponibilidadeDataHora;
import com.usj.fastservice.models.Pedidos;
import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosPedidoDTO;
import com.usj.fastservice.models.mapper.PedidoMapper;
import com.usj.fastservice.models.mapper.ServicoMapper;
import com.usj.fastservice.models.mapper.UsuarioMapper;
import com.usj.fastservice.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ServicoService servicoService;
	
	
	public DadosPedidoDTO abrirPedido(Long idCliente, Long idServico, DadosPedidoDTO pedidoRequestDTO) throws Exception {
		try {
			Usuario cliente = usuarioService.readUsuarioRepositoryById(idCliente);
			UsuarioService.isUsuarioAtivo(cliente);
			Servicos servico = servicoService.readServicoRepositoryById(idServico);
			UsuarioService.isUsuarioAtivo(servico.getUsuario());
			if (!cliente.getId().equals(servico.getUsuario().getId())) {
				pedidoRequestDTO.setCliente(UsuarioMapper.toDto(cliente));
				pedidoRequestDTO.setServicoContratado(ServicoMapper.toDto(servico));
				Pedidos pedido = PedidoMapper.toModel(pedidoRequestDTO);			
				pedido.setUsuario(cliente); 
				pedido.setServico(servico);
				return PedidoMapper.toDto(pedidoRepository.save(pedido));				
			} else {
				throw new Exception("O usuário não pode contratar os serviços que oferece.");
			}
		} catch (Exception e) {
			return PedidoMapper.setMsg(null,e.getMessage());
		}
	}

	public DadosPedidoDTO atualizarDisponibilidadeDeHorarios(Long idUsuario, Long idPedido, DadosPedidoDTO disponibilidadeRequestDTO) throws Exception {
		try {
			Pedidos pedido = readPedidoRepositoryById(idPedido);
			UsuarioService.isUsuarioAtivo(pedido.getUsuario());
			Servicos servico = pedido.getServico();
			UsuarioService.isUsuarioAtivo(servico.getUsuario());
			Long profissional = servico.getUsuario().getId();
			if (profissional.equals(idUsuario)) {
				pedido.setDisponibilidade(disponibilidadeRequestDTO.getDisponibilidade());
				for (DisponibilidadeDataHora disponibilidade : pedido.getDisponibilidade()) {
					disponibilidade.setPedido(pedido);
					disponibilidade.setDataSelecionadaPeloUsuario(false);
				}
				pedido = pedidoRepository.save(pedido);
			} else {
				throw new Exception("Você não tem permissão para executar essa ação!"); 				
			}
			return PedidoMapper.toDto(pedido);
		} catch (Exception e) {
			return PedidoMapper.setMsg(null, e.getMessage());
		}
	}
	public DadosPedidoDTO finalizarPedido(Long idUsuario, Long idPedido, boolean fnsh) throws Exception {
		try { 
			Pedidos pedido = readPedidoRepositoryById(idPedido);
			UsuarioService.isUsuarioAtivo(pedido.getUsuario());
			Servicos servico = pedido.getServico();
			UsuarioService.isUsuarioAtivo(servico.getUsuario());
			Long cliente = pedido.getUsuario().getId();
			Long profissional = servico.getUsuario().getId();
				if (cliente.equals(idUsuario)) {
					pedido.setServicoFinalizadoPeloUsuario(fnsh);
				} else if (profissional.equals(idUsuario)) {
					pedido.setServicoFinalizadoPeloProfissional(fnsh); 
				} else {
					throw new Exception("Você não tem permissão para executar essa ação!"); 				
				}
				pedidoRepository.save(pedido);
			return PedidoMapper.toDto(pedido);
		} catch (Exception e) {
			return PedidoMapper.setMsg(null, e.getMessage());
		}
	}
	
	public DadosPedidoDTO usuarioSelecionaDataHora(Long idUsuario, Long idPedido, Long idDataHora) throws Exception {
		try {
			Pedidos pedido = readPedidoRepositoryById(idPedido);
			UsuarioService.isUsuarioAtivo(pedido.getUsuario());
			Servicos servico = pedido.getServico();
			UsuarioService.isUsuarioAtivo(servico.getUsuario());
			Long cliente = pedido.getUsuario().getId();
		if (cliente.equals(idUsuario)) {
			if (pedido.getDisponibilidade().isEmpty()) {
				throw new Exception("Em breve o usuario "+ servico.getUsuario().getNome() +" deverá liberar algumas datas e horas disponíveis para executar o serviço.");
			}
			for (DisponibilidadeDataHora disponibilidadeDataHora : pedido.getDisponibilidade()) {
				if (disponibilidadeDataHora.isDataSelecionadaPeloUsuario()) {
					throw new Exception("A data e hora já foram selecionadas. Entre em contato com o "+ servico.getUsuario().getNome() +" para alterar.");
				}
			}
			for (DisponibilidadeDataHora disponibilidadeDataHora : pedido.getDisponibilidade()) {
				if (disponibilidadeDataHora.getId().equals(idDataHora)) {
					disponibilidadeDataHora.setDataSelecionadaPeloUsuario(true);
				}
			}
			pedidoRepository.save(pedido);
		} else { 
			throw new Exception("Você não tem permissão para executar essa ação!"); 
			}
		return PedidoMapper.toDto(pedido);
		} catch (Exception e) {
			return PedidoMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosPedidoDTO usuarioAvaliarServico(Long idUsuario, Long idPedido, Integer av) throws Exception {
		try {
		Pedidos pedido = readPedidoRepositoryById(idPedido);
		UsuarioService.isUsuarioAtivo(pedido.getUsuario());
		Servicos servico = pedido.getServico();
		UsuarioService.isUsuarioAtivo(servico.getUsuario());
		Long cliente = pedido.getUsuario().getId();
		if (cliente.equals(idUsuario))
			if (pedido.isServicoFinalizadoPeloUsuario()) {
				pedido.setAvaliacaoDoServicoPeloUsuario(String.valueOf(av));
				pedidoRepository.save(pedido);
			} else {
				throw new Exception("O pedido ainda não foi finalizado!"); 			
		} else {
			throw new Exception("Você não tem permissão para executar essa operação!"); 			
		}
		return PedidoMapper.toDto(pedido);
		} catch (Exception e) {
			return PedidoMapper.setMsg(null, e.getMessage());
		}
	}

	public List<DadosPedidoDTO> carregarPedidosDeClientes(Long idUsuario) throws Exception {
		try {
			List<Pedidos> pedidos = pedidoRepository.findAllByUsuarioId(idUsuario);
			if (!pedidos.isEmpty()) {
				return PedidoMapper.toListDto(pedidos);
			} else {
				throw new Exception("Não há pedidos em andamento.");
			}
		} catch (Exception e) {
			List<DadosPedidoDTO> listaServico = new ArrayList<DadosPedidoDTO>();
			listaServico.add(PedidoMapper.setMsg(null, e.getMessage()));
			return listaServico;
		}
	}
	
	public List<DadosPedidoDTO> carregarPedidosDeProfissional(Long idUsuario) throws Exception {
		try {
			List<Servicos> servicos = servicoService.readAllServicosRepository(idUsuario);
			if (!servicos.isEmpty()) {
				UsuarioService.isUsuarioAtivo(servicos.get(0).getUsuario());
				List<Pedidos> pedidos = new ArrayList<Pedidos>();
				for (Servicos servico : servicos) {
					pedidos.addAll(servico.getPedidos());
				}
				if (!pedidos.isEmpty()) {
					return PedidoMapper.toListDto(pedidos);
				} else {
					throw new Exception("Não há pedidos para nenhum dos serviços oferecido.");
				}
			} else {
				throw new Exception("Não há serviços oferecidos.");
			}
		} catch (Exception e) {
			List<DadosPedidoDTO> listaServico = new ArrayList<DadosPedidoDTO>();
			listaServico.add(PedidoMapper.setMsg(null, e.getMessage()));
			return listaServico;
		}
	}

	public List<DadosPedidoDTO> carregarPedidosByFilter(Long idUsuario, Boolean servicofinalizado) {
		try {
			List<DadosPedidoDTO> pedidos = carregarPedidosDeClientes(idUsuario);
			List<DadosPedidoDTO> pedidosFiltrados = new ArrayList<DadosPedidoDTO>();
				for (DadosPedidoDTO pedido : pedidos) {
					if (Boolean.parseBoolean(pedido.getServicoFinalizadoCliente()) == servicofinalizado) {
						if (Boolean.parseBoolean(pedido.getServicoFinalizadoProfissional()) == servicofinalizado) {
							pedidosFiltrados.add(pedido);
						}
					}
				}
				if (!pedidosFiltrados.isEmpty()) {
					return pedidosFiltrados;
				} else {
					throw new Exception("Não há pedidos para exibir com o filtro aplicado.");				
				}
		} catch (Exception e) {
			List<DadosPedidoDTO> listaServico = new ArrayList<DadosPedidoDTO>();
			listaServico.add(PedidoMapper.setMsg(null, e.getMessage()));
			return listaServico;
		}
	}
	
	public DadosPedidoDTO carregarPedido(Long requisitante, Long idPedido) throws Exception {
		try {
			Pedidos pedido = readPedidoRepositoryById(idPedido);
			Long cliente = pedido.getUsuario().getId();
			Long profissional =pedido.getServico().getUsuario().getId();
			if (cliente.equals(requisitante) || profissional.equals(requisitante)) {
				UsuarioService.isUsuarioAtivo(pedido.getUsuario());
				UsuarioService.isUsuarioAtivo(pedido.getServico().getUsuario());
				return PedidoMapper.toDto(pedido);
			} else {
				throw new Exception("Não há permissão para executar essa operação.");
			}
		} catch (Exception e) {
			return PedidoMapper.setMsg(null, e.getMessage());
		}
	}
	
	Pedidos readPedidoRepositoryById(Long idPedido) throws Exception {
		return pedidoRepository.findById(idPedido).orElseThrow(() -> new Exception("Pedido não encontrado!"));
	}
}
