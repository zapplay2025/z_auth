 package com.zapplay.auth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapplay.auth.model.User;
import com.zapplay.auth.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/home/users")
@CrossOrigin



public class UserController {
	
	@Autowired
	private UserService userServices;
	
	
	@GetMapping("/")
	public List<User>getAllUser(){
		System.out.println("refrf rerfr");
		return this.userServices.getAllUser();
	}
	
	@GetMapping("/get-LogedIn-user")
	public String loggedInUser(Principal principal) {
		System.err.println("77");
		return	principal.getName();
	}
	
	
	
	@GetMapping("/{name}")
	public User getByName(@PathVariable String name) {
		
		System.err.println("kunal");
		return this.userServices.getByName(name);
	}
	
	
	
	@PostMapping
	public User addUser(@RequestBody User userDto) {
		return this.userServices.addUser(userDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
