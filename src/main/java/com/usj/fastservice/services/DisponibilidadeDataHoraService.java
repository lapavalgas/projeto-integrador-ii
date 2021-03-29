package com.usj.fastservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.DisponibilidadeDataHora;
import com.usj.fastservice.repositories.DisponibilidadeDataHoraRepository;

@Service
public class DisponibilidadeDataHoraService {
	
	@Autowired
	private DisponibilidadeDataHoraRepository disponibilidadeDataHoraRepository;
	
	
	public DisponibilidadeDataHora save(DisponibilidadeDataHora disponibilidadeDataHora) {
		return disponibilidadeDataHoraRepository.save(disponibilidadeDataHora);
	}
	
	public List<DisponibilidadeDataHora> list() {
		return disponibilidadeDataHoraRepository.findAll();
	}
	
	public DisponibilidadeDataHora read(Long id) throws Exception {
		return disponibilidadeDataHoraRepository.findById(id).orElseThrow(() -> new Exception("Disponibilidade de data e hora não encontrada!"));
	}
	
	public void delete(Long id) {
		disponibilidadeDataHoraRepository.deleteById(id);
	}

}
