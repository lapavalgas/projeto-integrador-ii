package com.usj.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public Usuario create(@RequestBody DadosUsuarioDTO usuarioCadastroRequestDTO) {
		return usuarioService.cadastroDeUsuario(usuarioCadastroRequestDTO);
	}

	@GetMapping(value = "/{id}")
	public DadosUsuarioDTO login(@PathVariable Long id) throws Exception {
		return usuarioService.logarUsuario(id);
	}

	@GetMapping(value = "/{id}")
	public Usuario read(@PathVariable Long id) throws Exception {
		return usuarioService.carregarDadosDoUsuario(id);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable Long id) throws Exception {
		return usuarioService.deletarUsuario(id);
	}

}
