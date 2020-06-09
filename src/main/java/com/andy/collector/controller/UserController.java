package com.andy.collector.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.collector.model.User;
import com.andy.collector.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private final UserRepository userRepository;
	
	UserController(@Autowired UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user) throws SQLException {
		User userNew = userRepository.addUser(user);
		
		if(userNew != null) {
			return new ResponseEntity<User>(userNew, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
		try {
			userRepository.deleteUser(id);
			return new ResponseEntity<String>("User deleted.",HttpStatus.OK);
		} catch (SQLException ex) {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/login/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> showUser(@RequestBody User user, @PathVariable("id") String id) throws SQLException{
		boolean out = userRepository.login(user, id);
		
		if(out) {
			return new ResponseEntity<Boolean>(out, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(out, HttpStatus.UNAUTHORIZED);
		}
	}
	
}
