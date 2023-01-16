package com.examplenative.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "v1/")
@RestController
public class Healthcheck {
	
	@GetMapping("healthcheck")
	public ResponseEntity<?> healthCheck(){
		var body = new HashMap<>();
		body.put("application", "example-native");
		body.put("status", "UP");
		
		return ResponseEntity.status(HttpStatus.OK).body(body);
		
	}

}
