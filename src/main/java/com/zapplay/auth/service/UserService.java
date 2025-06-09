package com.zapplay.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zapplay.auth.model.User;
import com.zapplay.auth.repo.UserRepository;



@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
	
	List<User> list = new ArrayList<>();

//	public UserServices() {
//		this.list.add(new User("name", "password", "role"));
//
//	}

	public List<User> getAllUser() {
		return this.list;
	}

	public User getByName(String name) {
		return this.list.stream().filter(user -> user.getUsername().equalsIgnoreCase(name)).findAny().orElse(null);
	}

	public User addUser(User user) {
		this.list.add(user);

		return user;
	}

}
