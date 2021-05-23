package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Complemento;
import com.usj.fastservice.repositories.ComplementoRepository;

@Service
public class ComplementoService {
	
	@Autowired
	private ComplementoRepository complementoRepository;
	
	
	public Complemento save(Complemento complemento) {
		return complementoRepository.save(complemento);
	}
	
	public List<Complemento> list() {
		return complementoRepository.findAll();
	}
	
	public Complemento read(Long id) throws Exception {
		return complementoRepository.findById(id).orElseThrow(() -> new Exception("Complemento de endereço não encontrado!"));
	}
	
	public void delete(Long id) {
		complementoRepository.deleteById(id);
	}

}
