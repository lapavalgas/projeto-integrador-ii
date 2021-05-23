package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Endereco;
import com.usj.fastservice.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public List<Endereco> list() {
		return enderecoRepository.findAll();
	}
	
	public Endereco read(Long id) throws Exception {
		return enderecoRepository.findById(id).orElseThrow(() -> new Exception("Endereço não encontrado!"));
	}
	
	public void delete(Long id) {
		enderecoRepository.deleteById(id);
	}


}
