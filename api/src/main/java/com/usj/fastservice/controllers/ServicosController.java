package com.usj.fastservice.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.fastservice.models.dto.DadosServicoDTO;
import com.usj.fastservice.services.ServicoService;

@RestController
@RequestMapping("/usuarios/{idUsuario}")
public class ServicosController {

	@Autowired
	ServicoService servicoService;

	@CrossOrigin
	@PostMapping(value="/servicos") 
	public DadosServicoDTO create(@PathVariable Long idUsuario, @RequestBody DadosServicoDTO servicoRequestDto) throws Exception {
		return servicoService.cadastroDeServico(idUsuario, servicoRequestDto);
	}

	@CrossOrigin
	@GetMapping(value="/servicos") 
	public List<DadosServicoDTO> readByUserId(@PathVariable Long idUsuario) throws Exception {
		return servicoService.carregarServicos(idUsuario);
	}

	@CrossOrigin
	@GetMapping(value="/servicos/{idServico}") 
	public DadosServicoDTO readByServicoId(@PathVariable Long idUsuario, @PathVariable Long idServico) throws Exception {
		return servicoService.carregarDadosDoServico(idServico);
	}

	// TODO : implementar paginação (para limitar número de serviços)
	@CrossOrigin
	@GetMapping(value ="/servicos/marketplace")
	public List<DadosServicoDTO> readAll(@PathVariable Long idUsuario){
		return servicoService.carregarServicos();		
	}	

	@CrossOrigin
	@DeleteMapping("/servicos/{idServico}")
	public DadosServicoDTO delete(@PathVariable Long idUsuario, @PathVariable Long idServico, @PathParam(value="") boolean stts) throws Exception {
		return servicoService.setStatusServicos(idUsuario,idServico,stts);
	}
		
}