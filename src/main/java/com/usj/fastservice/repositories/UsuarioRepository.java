package com.usj.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	
}
