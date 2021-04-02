package com.usj.fastservice.controllers;

import java.util.Collection;
import java.util.Collections;
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

import com.usj.fastservice.models.DisponibilidadeDataHora;
import com.usj.fastservice.models.Pedidos;
import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.services.DisponibilidadeDataHoraService;
import com.usj.fastservice.services.PedidoService;
import com.usj.fastservice.services.UsuarioService;

@RestController
@RequestMapping("/usrs")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService; 
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	DisponibilidadeDataHoraService disponibilidadeDataHoraService;
	
	
	@PostMapping(value="/{id}/pedido") 
	public Pedidos create(@RequestBody Pedidos pedido, @PathVariable Long id) {
		try {
			Usuario usuario = new Usuario();
			usuario = usuarioService.carregarDadosContato(id);
			pedido.setUsuario(usuario);
			return pedidoService.save(pedido);			
			
		} catch (Exception e) {
			System.out.println("Não foi possível realizar o pedido :(");
			return null;
		}			
	}
	
	@PutMapping("/{idUser}/pedido/{idPedido}")
	public Pedidos updateDisponibilidadeDataHora(@RequestBody DisponibilidadeDataHora disponibilidadeDataHora, @PathVariable Long idUser, @PathVariable Long idPedido) throws Exception {
			Usuario usuario = new Usuario();
			usuario = usuarioService.carregarDadosContato(idUser);
			
			Pedidos pedido = new Pedidos();
			pedido.setUsuario(usuario);
			pedidoService.read(idPedido);
			
			try {
				if(usuario.equals(pedido.getServico().getUsuario())) {
					pedido.getDisponibilidade().add(disponibilidadeDataHora);		
				} 				
				return pedidoService.save(pedido);							
				
			} catch (Exception e) {
				System.out.println("Não foi possível atualizar o pedido.");
				return null;					
			}
		}
	
	@PutMapping("/{idUsr}/pedido/{idPedido}/disponibilidade/{idDataHora}")
	public Pedidos updateConfirmacaoUsuario(@PathVariable Long idUsr, @PathVariable Long idPedido, @PathVariable Long idDataHora) throws Exception {
		
		try {
			Usuario usuario = new Usuario();
			usuario = usuarioService.carregarDadosContato(idUsr);
			
			Pedidos pedido = new Pedidos();
			pedido.setUsuario(usuario);
			pedidoService.read(idPedido);
			
			if(usuario.equals(pedido.getUsuario())) {
				DisponibilidadeDataHora disponibilidade = disponibilidadeDataHoraService.read(idDataHora);
				
				for(DisponibilidadeDataHora d : pedido.getDisponibilidade()) {
					
					if (d.equals(disponibilidade)) {
						d.setDataSelecionadaPeloUsuario(true);
					}
					else {
						d.setDataSelecionadaPeloUsuario(false);
					}
				}				
			}
			
			return pedidoService.save(pedido);
					
		} catch (Exception e) {
			System.out.println("Não foi possível atualizar o pedido.");
			return null;
		}	
	}
	
	@PutMapping("/{idUsr}/pedido/{idPedido}")
	public Pedidos updateAvaliacaoServico(@PathVariable Long idUsr, @PathVariable Long idPedido, @PathParam(value="null") Integer av) throws Exception {
		try {
			Usuario usuario = usuarioService.carregarDadosContato(idUsr);
			Pedidos pedido = pedidoService.read(idPedido);	
			
			if(usuario.equals(pedido.getUsuario())) {
				pedido.setAvaliacaoDoServicoPeloUsuario(av);			
			}
			return pedidoService.save(pedido);
			
		} catch (Exception e) {
			System.out.println("Não foi possível atualizar o pedido.");
			return null;
		}
	}
	
	@GetMapping("/{id}/pedidos")
	public Collection<Pedidos> getListaPedidosRealizados(@PathVariable Long id) throws Exception {		
		try {
			return usuarioService.carregarDadosContato(id).getPedidosRealizados();	
			
		} catch (Exception e) {
			System.out.println("Não foi possível localizar pedidos.");
			return null;
		}
	}
		
	@GetMapping("/{id}/pedidos")
	public Collection<Pedidos> getListaPedidosFinalizadosOuEmProcessamento(@PathVariable Long id, @PathParam(value="null") Boolean atvs) throws Exception {		
		try {
			return usuarioService.carregarDadosContato(id).getPedidosRealizados();	
		} catch (Exception e) {
			System.out.println("Não foi possível localizar pedidos.");
			return null;
		}
	}
		
	@GetMapping("/{idUsr}/pedidos/{idPedido}")
	public Pedidos getPedidoById(@PathVariable Long idUsr, @PathVariable Long idPedido) throws Exception {		
		try {
			Usuario usuario = usuarioService.carregarDadosContato(idUsr);
			Pedidos pedido = pedidoService.read(idPedido);	
			
			if(usuario.equals(pedido.getUsuario())) {
				
			}
			return pedidoService.read(idPedido);
			
		} catch (Exception e) {
			System.out.println("Não foi possível localizar pedidos.");
			return null;
		}
	}
	
	
	
	
	
		
}

















