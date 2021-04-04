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
	
	public static List<DadosPedidoDTO> toListDto(List<Pedidos> pedidos){
		List<DadosPedidoDTO> listaServicosDto = new ArrayList<DadosPedidoDTO>();
		for (Pedidos pedido : pedidos) { listaServicosDto.add(toDto(pedido)); }
		return listaServicosDto;
	}
	
	public static List<Pedidos> toListModel(List<DadosPedidoDTO> pedidosDto){
		List<Pedidos> listaServicos = new ArrayList<Pedidos>();
		for (DadosPedidoDTO pedidoDto : pedidosDto) { listaServicos.add(toModel(pedidoDto)); }
		return listaServicos;
	}
	
	public static Pedidos toModel(DadosPedidoDTO dadosPedidoDTO) {
		return Pedidos.builder()
				.id(dadosPedidoDTO.getPedido_id())
				.usuario(UsuarioMapper.toModel(dadosPedidoDTO.getCliente()))
				.servico(ServicoMapper.toModel(dadosPedidoDTO.getServicoContratado()))
				.disponibilidade(dadosPedidoDTO.getDisponibilidade())
				.formaDePagamento(dadosPedidoDTO.getFormaDePagamento())
				.servicoFinalizadoPeloUsuario(Boolean.parseBoolean(dadosPedidoDTO.getServicoFinalizadoCliente()))
				.servicoFinalizadoPeloProfissional(Boolean.parseBoolean(dadosPedidoDTO.getServicoFinalizadoProfissional()))
				.avaliacaoDoServicoPeloUsuario(dadosPedidoDTO.getAvaliacaoDoCliente())
				.build();
	}
	
	public static DadosPedidoDTO toDto(Pedidos pedido) {
		return DadosPedidoDTO.builder()
				.pedido_id(pedido.getId())
				.cliente(UsuarioMapper.toLoggedUsuarioDTO(pedido.getUsuario()))
				.servicoContratado(ServicoMapper.toDto(pedido.getServico()))
				.disponibilidade(pedido.getDisponibilidade())
				.formaDePagamento(pedido.getFormaDePagamento())
				.servicoFinalizadoCliente(String.valueOf(pedido.isServicoFinalizadoPeloUsuario()))
				.servicoFinalizadoProfissional(String.valueOf(pedido.isServicoFinalizadoPeloProfissional()))
				.avaliacaoDoCliente(pedido.getAvaliacaoDoServicoPeloUsuario())
				.build();
	}
}
