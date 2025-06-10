package com.zapplay.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapplay.auth.jwt.JwtHelper;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")

public class ValidateController {

	@Autowired
	private JwtHelper jwtHelper;

	@RequestMapping("/validate")
	public Claims getClaims(String token) {

		Claims allClaims = jwtHelper.extractAllClaims(token);
		return allClaims;
	}

}
