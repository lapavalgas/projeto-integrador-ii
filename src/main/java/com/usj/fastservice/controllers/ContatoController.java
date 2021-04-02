package com.usj.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.services.ContatoService;

@RestController
@RequestMapping("/usuarios")
public class ContatoController {
	
	@Autowired
	ContatoService contatoService;
	
	@GetMapping("/{id}/contatos")
	public DadosUsuarioDTO read(@PathVariable Long id) throws Exception {
		return contatoService.carregarDadosContato(id);
	}
	
	@PutMapping("/{id}/contatos")
	public DadosUsuarioDTO update(@RequestBody DadosUsuarioDTO contato, @PathVariable Long id) throws Exception {
		return contatoService.atualizarDadosContato(contato, id);
	}

}
