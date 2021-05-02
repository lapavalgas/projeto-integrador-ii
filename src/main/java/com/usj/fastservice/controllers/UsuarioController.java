package com.usj.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@CrossOrigin
	@PostMapping
	public DadosUsuarioDTO create(@RequestBody DadosUsuarioDTO usuarioCadastroRequestDTO) {
		return usuarioService.cadastroDeUsuario(usuarioCadastroRequestDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/login/{idUsuario}")
	public DadosUsuarioDTO login(@PathVariable Long idUsuario) throws Exception {
		return usuarioService.logarUsuario(idUsuario);
	}

	@CrossOrigin
	@GetMapping(value = "/{idUsuario}")
	public DadosUsuarioDTO read(@PathVariable Long idUsuario) throws Exception {
		return usuarioService.carregarDadosDoUsuario(idUsuario);
	}

	@CrossOrigin
	@DeleteMapping(value = "/{idUsuario}")
	public DadosUsuarioDTO delete(@PathVariable Long idUsuario) throws Exception {
		return usuarioService.deletarUsuario(idUsuario);
	}

}
