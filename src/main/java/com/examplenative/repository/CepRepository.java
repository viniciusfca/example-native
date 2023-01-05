package com.examplenative.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplenative.model.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

	Optional<Cep> findByCep(String cep);
}
