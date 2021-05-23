package com.usj.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.DisponibilidadeDataHora;

@Repository
public interface DisponibilidadeDataHoraRepository extends JpaRepository<DisponibilidadeDataHora, Long>{

}
