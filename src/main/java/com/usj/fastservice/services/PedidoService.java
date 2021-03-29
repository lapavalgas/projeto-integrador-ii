package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Pedidos;
import com.usj.fastservice.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Pedidos save(Pedidos pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public List<Pedidos> list() {
		return pedidoRepository.findAll();
	}
	
	public Pedidos read(Long id) throws Exception {
		return pedidoRepository.findById(id).orElseThrow(() -> new Exception("Pedido não encontrado!"));
	}
	
	public void delete(Long id) {
		pedidoRepository.deleteById(id);
	}
}
