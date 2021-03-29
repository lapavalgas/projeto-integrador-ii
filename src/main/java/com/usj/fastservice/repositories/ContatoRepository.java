package com.usj.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
