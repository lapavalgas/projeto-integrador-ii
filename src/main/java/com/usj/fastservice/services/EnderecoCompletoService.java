package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.repositories.EnderecoCompletoRepository;

@Service
public class EnderecoCompletoService {

	@Autowired
	private EnderecoCompletoRepository enderecoCompletoRepository;
	
	
	public EnderecoCompleto save(EnderecoCompleto enderecoCompleto) {
		return enderecoCompletoRepository.save(enderecoCompleto);
	}
	
	public List<EnderecoCompleto> list() {
		return enderecoCompletoRepository.findAll();
	}
	
	public EnderecoCompleto read(Long id) throws Exception {
		return enderecoCompletoRepository.findById(id).orElseThrow(() -> new Exception("Endereço completo não encontrado!"));
	}
	
	public void delete(Long id) {
		enderecoCompletoRepository.deleteById(id);
	}
}
