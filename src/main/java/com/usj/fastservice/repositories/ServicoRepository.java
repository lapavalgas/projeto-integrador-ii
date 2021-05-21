package com.usj.fastservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.Servicos;

@Repository
public interface ServicoRepository extends JpaRepository<Servicos, Long>{
	
	// TODO : pode ser que de errado; então substituir ID (Long) por Usuario (objeto)
	List<Servicos> findAllByUsuarioId(Long usuarioId);
	
}
