package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> list() {
		return usuarioRepository.findAll();
	}
	
	public Usuario read(Long id) throws Exception {
		return usuarioRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
	}
	
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

}
