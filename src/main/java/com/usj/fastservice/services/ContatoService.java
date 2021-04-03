package com.usj.fastservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.usj.fastservice.models.Contato;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.models.mapper.UsuarioMapper;
import com.usj.fastservice.repositories.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	public DadosUsuarioDTO carregarDadosContato(Long id) throws Exception {
		try {
			Contato contato = readContatoRepositoryById(id);
			UsuarioService.isUsuarioAtivo(contato.getUsuario());
			return UsuarioMapper.toUsuarioDTO(contato);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosUsuarioDTO atualizarDadosContato(@RequestBody DadosUsuarioDTO contatoRequestResponseDTO, @PathVariable Long id) throws Exception {
		try {
			Contato contato = readContatoRepositoryById(id);
			UsuarioService.isUsuarioAtivo(contato.getUsuario());
			contato.setEmail(contatoRequestResponseDTO.getEmail());
			contato.setTelefone(contatoRequestResponseDTO.getTelefone());
			contatoRepository.save(contato);
			return UsuarioMapper.toUsuarioDTO(contato);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}
	
	Contato readContatoRepositoryById(Long id) throws Exception {
		return contatoRepository.findById(id).orElseThrow(() -> new Exception("Não foi possível localizar o contato :("));
	}

}
