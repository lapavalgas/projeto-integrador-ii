package com.usj.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.fastservice.models.Complemento;


@Repository
public interface ComplementoRepository extends JpaRepository<Complemento, Long> {

}
