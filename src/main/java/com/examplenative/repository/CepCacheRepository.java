package com.examplenative.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examplenative.model.CepCache;

@Repository
public interface CepCacheRepository extends CrudRepository<CepCache, String> {

	CepCache findByCep(String cep);

}
