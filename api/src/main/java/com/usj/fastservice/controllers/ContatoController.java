package com.usj.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.services.ContatoService;

@RestController
@RequestMapping("/usuarios/{idUsuario}")
public class ContatoController { 
	
	@Autowired
	ContatoService contatoService;

	@CrossOrigin
	@GetMapping("/contatos")
	public DadosUsuarioDTO read(@PathVariable Long idUsuario) throws Exception {
		return contatoService.carregarDadosContato(idUsuario);
	}

	@CrossOrigin
	@PutMapping("/contatos")
	public DadosUsuarioDTO update(@PathVariable Long idUsuario, @RequestBody DadosUsuarioDTO contato) throws Exception {
		return contatoService.atualizarDadosContato(contato, idUsuario);
	}

}
