package com.zapplay.auth.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zapplay.auth.config.SecurityConfig;
import com.zapplay.auth.dto.AuthResponseDto;
import com.zapplay.auth.jwt.JwtHelper;
import com.zapplay.auth.jwt.JwtResponse;
import com.zapplay.auth.model.User;
import com.zapplay.auth.repo.UserRepository;
import com.zapplay.auth.service.UserService;

@RestController
@RequestMapping("/auth")

@CrossOrigin(origins = "*")
public class AuthController {

	private final SecurityConfig securityConfig;

	@Autowired
	private UserRepository repository;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserService userService;

	AuthController(SecurityConfig securityConfig) {
		this.securityConfig = securityConfig;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

		System.err.println("log ---------------------in");
		UserDetails userDetails = null;
		try {

			this.doAuthenticate(email, password);
			System.out.println(email);
			 userDetails = userDetailsService.loadUserByUsername(email);

		} catch (Exception e) {

			System.out.println("kunal");
			return ResponseEntity
			        .status(HttpStatus.UNAUTHORIZED)
			        .body(Map.of("message", "Invalid email or password"));

		}
		System.err.println("kunal");
		User user = userService.findByEmail(email);
		String token = this.helper.generateToken(userDetails, email, user.getUsername());

		user.setPassword(null);

		AuthResponseDto response = new AuthResponseDto();
		response.setToken(token);
		response.setUser(user);
		response.setMessage("Login successful");

		return ResponseEntity.ok(response);

	}

	private void doAuthenticate(String name, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(name, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("sign-up")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		System.err.println("Sinup handeller");
		User byUsername = repository.findByUsername(user.getUsername());
		User byEmail = repository.findByEmail(user.getEmail());

		System.out.println(user.getUsername());

		User byPhone = repository.findByPhone(user.getPhone());

		if (byUsername != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Name Already Exist..");
		} else if (byEmail != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist..");
		} else if (byPhone != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number Already Exist..");
		}

		user.setPassword(passwordEncoder().encode(user.getPassword()));
		User save = repository.save(user);
		return ResponseEntity.ok(save);

//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
