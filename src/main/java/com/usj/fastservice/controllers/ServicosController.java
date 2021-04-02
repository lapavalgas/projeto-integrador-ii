package com.usj.fastservice.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.services.ServicoService;
import com.usj.fastservice.services.UsuarioService;

@RestController
@RequestMapping("/usrs")
public class ServicosController {

	@Autowired
	ServicoService servicoDAO;
	
	@Autowired
	UsuarioService usuarioDAO;
	
	@GetMapping(value="/{id}/servicos") 
	public Servicos getServicoById(@PathVariable Long id) throws Exception {
		try {
			return servicoDAO.read(id);
		} catch (Exception e) {
			System.out.println("Serviço não encontrado!");
			return null;
		}	
	}
	
	@PostMapping(value="/{id}/servicos") 
	public Servicos create(@RequestBody Servicos servico, @PathVariable Long id) {
		try {
			Usuario usuario = new Usuario();
			usuario = usuarioDAO.carregarDadosContato(id);
			servico.setUsuario(usuario);
			return servicoDAO.save(servico);			
			
		} catch (Exception e) {
			System.out.println("Usuário não encontrado!");
			return null;
		}			
	}
	
	@DeleteMapping("/{idUsr}/servico/{idServico}")
	public void delete(@PathVariable Long idUsr, @PathVariable Long idServico, @PathParam(value="true") Boolean stts) throws Exception {
		Usuario usuario = usuarioDAO.carregarDadosContato(idUsr);		
		Servicos servico = servicoDAO.read(idServico);		
		try {
			if(usuario.equals(servico.getUsuario())) {
				stts = false;
			} 				
		} catch (Exception e) {
			System.out.println("Usuário ou serviço não encontrados!");			
		}
	}
	
	
	
	
	
	
}
