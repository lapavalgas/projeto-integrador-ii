package com.usj.fastservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.Pedidos;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Long>{
	
	// TODO : pode ser que de errado; então substituir ID (Long) por Usuario (objeto)
	List<Pedidos> findAllByUsuarioId(Long usuarioId);
}
