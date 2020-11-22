package com.springboot.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.entity.AuthRequest;
import com.springboot.jwt.util.JWTUtil;

@RestController
@RequestMapping("/jwt/token/v1")
public class JWTTOkenGenaratorController {

	@Autowired
	private JWTUtil jWTUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/generateToken")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid User Id and Password");
		}

		return jWTUtil.generateToken(authRequest.getUsername());
	}
}
