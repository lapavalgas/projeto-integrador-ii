package com.usj.fastservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.models.mapper.EnderecoMapper;
import com.usj.fastservice.models.mapper.UsuarioMapper;
import com.usj.fastservice.repositories.EnderecoCompletoRepository;

@Service
public class EnderecoCompletoService {

	@Autowired
	private EnderecoCompletoRepository enderecoCompletoRepository;

	public DadosUsuarioDTO carregarDadosEndereco(Long id) throws Exception {
		try {
			EnderecoCompleto enderecoCompleto = readEnderecoCompletoRepositoryById(id);
			UsuarioService.isUsuarioAtivo(enderecoCompleto.getUsuario());
			return UsuarioMapper.toEndereco(enderecoCompleto);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosUsuarioDTO atualizarEndereco(DadosUsuarioDTO enderecoAtualizar, Long id) throws Exception {
		try {
			EnderecoCompleto enderecoCompleto = EnderecoMapper.toReplace(enderecoAtualizar, readEnderecoCompletoRepositoryById(id));
			UsuarioService.isUsuarioAtivo(enderecoCompleto.getUsuario());
			return UsuarioMapper.toEndereco(enderecoCompletoRepository.save(enderecoCompleto));
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}
	
	EnderecoCompleto readEnderecoCompletoRepositoryById(Long id) throws Exception {
		return enderecoCompletoRepository.findById(id).orElseThrow(() -> new Exception("Não foi possível localizar o endereço :("));
	}

}
