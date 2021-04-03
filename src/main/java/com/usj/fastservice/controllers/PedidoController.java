package com.usj.fastservice.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.dto.DadosPedidoDTO;
import com.usj.fastservice.services.PedidoService;

@RestController
@RequestMapping("/usuarios/{idUsuario}")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@PostMapping(value = "/pedido")
	public DadosPedidoDTO create(@PathVariable Long idUsuario, @RequestBody DadosPedidoDTO pedidoRequestDTO)
			throws Exception {
		return pedidoService.abrirPedido(idUsuario, pedidoRequestDTO);
	}

	@GetMapping("/pedidos/{idPedido}")
	public DadosPedidoDTO getPedidoById(@PathVariable Long idUsuario, @PathVariable Long idPedido) throws Exception {
		return pedidoService.carregarPedido(idUsuario, idPedido);
	}
	
	@GetMapping("/pedidos/cliente")
	public List<DadosPedidoDTO> readPedidosCliente(@PathVariable Long idUsuario) throws Exception {
		return pedidoService.carregarPedidosDeClientes(idUsuario);
	}
	
	@GetMapping("/pedidos/profissional")
	public List<DadosPedidoDTO> readPedidosProfissional(@PathVariable Long idUsuario) throws Exception {
		return pedidoService.carregarPedidosDeProfissional(idUsuario);
	}

	@GetMapping("/pedidos")
	public List<DadosPedidoDTO> readPedidosByFilter(@PathVariable Long idUsuario, @PathParam(value = "") Boolean filtroPorPedidos) throws Exception {
		return pedidoService.carregarPedidosByFilter(idUsuario, filtroPorPedidos);
	}

	@PutMapping("/pedidos/{idPedido}/datahora")
	public DadosPedidoDTO update(@PathVariable Long idUsuario, @PathVariable Long idPedido, @RequestBody DadosPedidoDTO disponibilidadeRequestDTO) throws Exception {
		return pedidoService.atualizarDisponibilidadeDeHorarios(idUsuario, idPedido, disponibilidadeRequestDTO);
	}

	@PutMapping("/pedidos/{idPedido}/disponibilidades/{idDataHora}")
	public DadosPedidoDTO updateDataSelecionada(@PathVariable Long idUsuario, @PathVariable Long idPedido, @PathVariable Long idDataHora) throws Exception {
		return pedidoService.usuarioSelecionaDataHora(idUsuario, idPedido, idDataHora);
	}

	@PutMapping("/pedidos/{idPedido}")
	public DadosPedidoDTO updateAvaliacaoServico(@PathVariable Long idUsuario, @PathVariable Long idPedido, @PathParam(value = "") Integer av) throws Exception {
		return pedidoService.usuarioAvaliarServico(idUsuario, idPedido, av);
	}	


}
