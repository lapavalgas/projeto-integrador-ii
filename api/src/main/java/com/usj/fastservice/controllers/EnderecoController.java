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
import com.usj.fastservice.services.EnderecoCompletoService;

@RestController
@RequestMapping("/usuarios/{idUsuario}")
public class EnderecoController {
	
	@Autowired
	EnderecoCompletoService enderecoCompletoService;

	@CrossOrigin
	@GetMapping("/enderecos")
	public DadosUsuarioDTO read(@PathVariable Long idUsuario) throws Exception {
		return enderecoCompletoService.carregarDadosEndereco(idUsuario);			
	}

	@CrossOrigin
	@PutMapping("/enderecos")
	public DadosUsuarioDTO update(@PathVariable Long idUsuario, @RequestBody DadosUsuarioDTO complemento) throws Exception {
		return enderecoCompletoService.atualizarEndereco(complemento, idUsuario);
	}
		
}
