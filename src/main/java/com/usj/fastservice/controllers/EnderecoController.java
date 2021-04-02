package com.usj.fastservice.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.services.EnderecoCompletoService;

@RestController
@RequestMapping("/usuarios")
public class EnderecoController {
	
	@Autowired
	EnderecoCompletoService enderecoCompletoService;
	
	@GetMapping("/{id}/enderecos")
	public DadosUsuarioDTO read(@PathVariable Long id) throws Exception {
		return enderecoCompletoService.carregarDadosEndereco(id);			
	}
	
	@PutMapping("/{id}/enderecos")
	public DadosUsuarioDTO update(@RequestBody DadosUsuarioDTO complemento, @PathVariable Long id) throws Exception {
		return enderecoCompletoService.atualizarEndereco(complemento, id);
	}
		
}
