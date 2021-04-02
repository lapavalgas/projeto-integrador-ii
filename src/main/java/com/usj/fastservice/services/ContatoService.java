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

	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}

	public DadosUsuarioDTO carregarDadosContato(Long id) throws Exception {
		Contato contato = contatoRepository.findById(id).orElseThrow(() -> new Exception("Não foi possível localizar o contato :("));
		return UsuarioMapper.toUsuarioDTO(contato);
	}

	public DadosUsuarioDTO atualizarDadosContato(@RequestBody DadosUsuarioDTO contatoRequestResponseDTO, @PathVariable Long id) throws Exception {
		Contato contato = contatoRepository.findById(id).orElseThrow(() -> new Exception("Não foi possível localizar o contato :("));
		contato.setEmail(contatoRequestResponseDTO.getEmail());
		contato.setTelefone(contatoRequestResponseDTO.getTelefone());
		contatoRepository.save(contato);
		return UsuarioMapper.toUsuarioDTO(contato);
	}

}
