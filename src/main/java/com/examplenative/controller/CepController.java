package com.examplenative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplenative.business.CepBusiness;
import com.examplenative.dto.CepDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RequestMapping(path = "v1/")
@RestController
public class CepController {
	
	@Autowired
	private CepBusiness business;

	@GetMapping(path = "cep/{cep}")
	public ResponseEntity<CepDTO> getCep(@PathVariable String cep) throws JsonMappingException, JsonProcessingException{
		return business.findByCep(cep);
		
	}
}
