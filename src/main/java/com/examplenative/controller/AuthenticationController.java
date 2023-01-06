package com.examplenative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplenative.model.User;
import com.examplenative.util.JwtUtil;

@RestController
@RequestMapping("v1/")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<String> auth(@RequestBody User user) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		
		if(userDetails != null) {
			return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.generateToken(userDetails));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Falha na geração do token, usuário não encontrado");
	}

}
