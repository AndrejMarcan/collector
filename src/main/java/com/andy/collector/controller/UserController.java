package com.andy.collector.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.collector.model.User;
import com.andy.collector.repository.UserRepository;
import com.andy.collector.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestBody User user) throws SQLException {
		userService.addNewUser(user);
		
	}
	
	@PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void editUser(@RequestBody User user, @PathVariable("id") String id) throws SQLException {
		userService.updateUserbyId(user, Integer.valueOf(id));
		
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable("id") String id){
		userService.deleteUser(Integer.valueOf(id));
	}
	
	@GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<User>> showUser(@PathVariable("id") String id) throws SQLException{
		Optional<User> userNew = userService.findUser(Integer.valueOf(id));
		
		if (userNew != null) {
			return new ResponseEntity<Optional<User>>(userNew, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/show/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> showUser() throws SQLException{
		List<User> userNew = userService.findAllUsers();
		
		if (userNew != null) {
			return new ResponseEntity<List<User>>(userNew, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
	}
	
}
