package com.usj.fastservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.usj.fastservice.models.DisponibilidadeDataHora;
import com.usj.fastservice.models.Pedidos;
import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosPedidoDTO;
import com.usj.fastservice.models.dto.DadosServicoDTO;
import com.usj.fastservice.models.mapper.PedidoMapper;
import com.usj.fastservice.repositories.PedidoRepository;
import com.usj.fastservice.repositories.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ServicoService servicoService;
	
	
	public DadosPedidoDTO abrirPedido(Long idUsuario, DadosPedidoDTO pedidoRequestDTO) throws Exception {
		Usuario usuario = usuarioService.readUsuarioRepositoryById(idUsuario);
		Servicos servico = servicoService.readServicoRepositoryById(pedidoRequestDTO.getServicoContratado().getServico_id());
		Pedidos pedido = PedidoMapper.toModel(pedidoRequestDTO);
		pedido.setUsuario(usuario);
		pedido.setServico(servico);
		return PedidoMapper.toPedidoDTO(pedidoRepository.save(pedido));
	}
	
	public Pedidos readPedidoRepositoryById(Long idPedido) throws Exception {
		return pedidoRepository.findById(idPedido).orElseThrow(() -> new Exception("Pedido não encontrado!"));
	}

	public DadosPedidoDTO atualizarDisponibilidadeDeHorarios(Long idUsuario, Long idPedido, DadosPedidoDTO disponibilidadeRequestDTO) throws Exception {
		Pedidos pedido = readPedidoRepositoryById(idPedido);
		Servicos servico = pedido.getServico();
		if (servico.getUsuario().getId() == idUsuario) {
			pedido.setDisponibilidade(disponibilidadeRequestDTO.getDisponibilidade());
			for (DisponibilidadeDataHora disponibilidade : pedido.getDisponibilidade()) {
				disponibilidade.setDataSelecionadaPeloUsuario(false);
			}
			pedido = pedidoRepository.save(pedido);
		}
		return PedidoMapper.toPedidoDTO(pedido);
	}
	
	public DadosPedidoDTO usuarioSelecionaDataHora(Long idUsuario, Long idPedido, Long idDataHora) throws Exception {
		Pedidos pedido = readPedidoRepositoryById(idPedido);
		Servicos servico = pedido.getServico();
		if (servico.getUsuario().getId() == idUsuario) {
			for (DisponibilidadeDataHora disponibilidade : pedido.getDisponibilidade()) {
				if (disponibilidade.getId() == idDataHora) {
					disponibilidade.setDataSelecionadaPeloUsuario(true);
				}
			}
			pedidoRepository.save(pedido);
		} else { throw new Exception("Operação invalida!"); }
		return PedidoMapper.toPedidoDTO(pedido);
	}

	public DadosPedidoDTO usuarioAvaliarServico(Long idUsuario, Long idPedido, Integer av) throws Exception {
		Pedidos pedido = readPedidoRepositoryById(idPedido);
		Servicos servico = pedido.getServico();
		if (pedido.getUsuario().getId() == idUsuario && pedido.isServicoFinalizadoPeloUsuario()) {
			pedido.setAvaliacaoDoServicoPeloUsuario(av);
			pedidoRepository.save(pedido);
		}		
		return PedidoMapper.toPedidoDTO(pedido);
	}

	public List<DadosPedidoDTO> carregarPedidosDeClientes(Long idUsuario) {
		List<Pedidos> pedidos = pedidoRepository.findAllByUsuarioId(idUsuario);
		return PedidoMapper.toListPedidoDTO(pedidos);
	}
	
	public List<DadosPedidoDTO> carregarPedidosDeProfissional(Long idUsuario) throws Exception {
		List<Servicos> servicos = servicoService.readAllServicosRepository(idUsuario);
		List<Pedidos> pedidos = new ArrayList<Pedidos>();
		for (Servicos servico : servicos) {
			pedidos.addAll(servico.getPedidos());
		}
		return PedidoMapper.toListPedidoDTO(pedidos);
	}

	public List<DadosPedidoDTO> carregarPedidosByFilter(Long idUsuario, Boolean filtroPedidosAbertos) {
		List<DadosPedidoDTO> pedidos = carregarPedidosDeClientes(idUsuario);
		List<DadosPedidoDTO> pedidosFiltrados = new ArrayList<DadosPedidoDTO>();
		for (DadosPedidoDTO pedido : pedidos) {
			if (pedido.isServicoFinalizadoCliente() == filtroPedidosAbertos) {
				pedidosFiltrados.add(pedido);
			}
		}
		return pedidosFiltrados;
	}
	
	public DadosPedidoDTO carregarPedido(Long idUsuario, Long idPedido) throws Exception {
		Pedidos pedido = readPedidoRepositoryById(idPedido);
		if (pedido.getUsuario().getId() == idUsuario || pedido.getServico().getUsuario().getId() == idUsuario) {
			return PedidoMapper.toPedidoDTO(pedido);
		} else {
			throw new Exception("Operação não pode ser realizada.");
		}
	}
}
