package com.usj.fastservice.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.usj.fastservice.models.Pedidos;
import com.usj.fastservice.models.dto.DadosPedidoDTO;

@Component
public class PedidoMapper {

	public static DadosPedidoDTO setMsg(Long id, String msg) {
		return DadosPedidoDTO.builder()
							.pedido_id(id)
							.msg(msg)
							.build();
	}
	
	public static List<DadosPedidoDTO> toListPedidoDTO(List<Pedidos> pedidos){
		List<DadosPedidoDTO> listaServicosDto = new ArrayList<DadosPedidoDTO>();
		for (Pedidos pedido : pedidos) { listaServicosDto.add(toPedidoDTO(pedido)); }
		return listaServicosDto;
	}
	
	public static List<Pedidos> toListPedidoModel(List<DadosPedidoDTO> pedidosDto){
		List<Pedidos> listaServicos = new ArrayList<Pedidos>();
		for (DadosPedidoDTO pedidoDto : pedidosDto) { listaServicos.add(toModel(pedidoDto)); }
		return listaServicos;
	}
	
	public static Pedidos toModel(DadosPedidoDTO dadosPedidoDTO) {
		return Pedidos.builder()
				.id(dadosPedidoDTO.getPedido_id())
				.usuario(UsuarioMapper.toUsuario(dadosPedidoDTO.getCliente()))
				.servico(ServicoMapper.toServicos(dadosPedidoDTO.getServicoContratado()))
				.disponibilidade(dadosPedidoDTO.getDisponibilidade())
				.formaDePagamento(dadosPedidoDTO.getFormaDePagamento())
				.servicoFinalizadoPeloUsuario(dadosPedidoDTO.isServicoFinalizadoCliente())
				.servicoFinalizadoPeloProfissional(dadosPedidoDTO.isServicoFinalizadoProfissional())
				.avaliacaoDoServicoPeloUsuario(Integer.parseInt(dadosPedidoDTO.getAvaliacaoDoCliente()))
				.build();
	}
	
	public static DadosPedidoDTO toPedidoDTO(Pedidos pedido) {
		return DadosPedidoDTO.builder()
				.pedido_id(pedido.getId())
				.cliente(UsuarioMapper.toLoggedUsuarioDTO(pedido.getUsuario()))
				.servicoContratado(ServicoMapper.toServicoDTO(pedido.getServico()))
				.disponibilidade(pedido.getDisponibilidade())
				.formaDePagamento(pedido.getFormaDePagamento())
				.servicoFinalizadoCliente(pedido.isServicoFinalizadoPeloUsuario())
				.servicoFinalizadoProfissional(pedido.isServicoFinalizadoPeloProfissional())
				.avaliacaoDoCliente(String.valueOf(pedido.getAvaliacaoDoServicoPeloUsuario()))
				.build();
	}
}
