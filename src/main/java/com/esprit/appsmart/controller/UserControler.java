package com.esprit.appsmart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.appsmart.model.User;
import com.esprit.appsmart.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/smartUser")
public class UserControler {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users/{login}/{password}")
	public User checkIfExist(@PathVariable("login") String login, @PathVariable("password") String password) {		
		return userRepository.checkIfExist(login, password);
	}
	
	 @GetMapping("/users")
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
}
