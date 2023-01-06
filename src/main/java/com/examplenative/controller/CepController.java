package com.examplenative.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplenative.business.CepBusiness;
import com.examplenative.dto.CepDTO;

@RequestMapping(path = "v1/")
@RestController
public class CepController {
	
	@Autowired
	private CepBusiness business;

	@GetMapping(path = "cep/{cep}")
	public ResponseEntity<CepDTO> getCep(@PathVariable String cep) throws IOException{
		return business.findByCep(cep);
		
	}
}
