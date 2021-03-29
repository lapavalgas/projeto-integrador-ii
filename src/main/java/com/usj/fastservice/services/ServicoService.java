package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.repositories.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	
	public Servicos save(Servicos servico) {
		return servicoRepository.save(servico);
	}
	
	public List<Servicos> list() {
		return servicoRepository.findAll();
	}
	
	public Servicos read(Long id) throws Exception {
		return servicoRepository.findById(id).orElseThrow(() -> new Exception("Serviço não encontrado!"));
	}

	public void delete(Long id) {
		servicoRepository.deleteById(id);
		
	}

}
