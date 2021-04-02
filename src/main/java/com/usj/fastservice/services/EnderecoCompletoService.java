package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.models.mapper.UsuarioMapper;
import com.usj.fastservice.repositories.EnderecoCompletoRepository;

@Service
public class EnderecoCompletoService {

	@Autowired
	private EnderecoCompletoRepository enderecoCompletoRepository;

	public DadosUsuarioDTO carregarDadosEndereco(Long id) throws Exception {
		EnderecoCompleto enderecoCompleto = enderecoCompletoRepository.findById(id).orElseThrow(() -> new Exception("Não foi possível localizar o endereço :("));
		return UsuarioMapper.toEndereco(enderecoCompleto);
	}

	public DadosUsuarioDTO atualizarEndereco(DadosUsuarioDTO enderecoAtualizar, Long id) throws Exception {
		EnderecoCompleto enderecoCompleto = enderecoCompletoRepository.findById(id).orElseThrow(() -> new Exception("Não foi possível localizar o endereço :("));

		DadosUsuarioDTO endereco; // = buscaNaAPICorreio();
		
		enderecoCompleto.getEndereco().setCep(enderecoAtualizar.getCep());
		enderecoCompleto.getEndereco().setEstado(null);
		enderecoCompleto.getEndereco().setMunicipio(null);
		enderecoCompleto.getEndereco().setBairro(null);
		enderecoCompleto.getEndereco().setLogradouro(null);
		enderecoCompleto.getComplemento().setNumero(enderecoAtualizar.getNumero());
		enderecoCompleto.getComplemento().setComplemento(enderecoAtualizar.getComplemento());
		
		return UsuarioMapper.toEndereco(enderecoCompletoRepository.save(enderecoCompleto));
	}

}
