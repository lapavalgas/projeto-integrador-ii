package com.usj.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.EnderecoCompleto;

@Repository
public interface EnderecoCompletoRepository extends JpaRepository<EnderecoCompleto, Long> {

}
