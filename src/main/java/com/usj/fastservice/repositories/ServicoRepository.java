package com.usj.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.Servicos;

@Repository
public interface ServicoRepository extends JpaRepository<Servicos, Long>{
	
}
