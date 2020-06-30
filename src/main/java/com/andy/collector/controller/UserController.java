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

import com.andy.collector.dto.UserDTO;
import com.andy.collector.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private UserService userService;
	
	UserController( @Autowired UserService userService){
		this.userService = userService;
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
		try {
			userService.addNewUser(user);
			return new ResponseEntity<String>("user saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("user not saved",HttpStatus.CONFLICT);
		}
		
	}
	
	@PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editUser(@RequestBody UserDTO user, @PathVariable("id") String id) throws SQLException {		
		try {
			userService.updateUserbyId(user, Integer.valueOf(id));
			return new ResponseEntity<String>("user saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("user saved",HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
		
		try {
			userService.deleteUser(Integer.valueOf(id));
			return new ResponseEntity<String>("user removed",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<UserDTO>> showUser(@PathVariable("id") String id) throws SQLException{
		Optional<UserDTO> userNew = userService.findUser(Integer.valueOf(id));
		
		if (userNew != null) {
			return new ResponseEntity<Optional<UserDTO>>(userNew, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<UserDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/show/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> showUser() throws SQLException{
		
		List<UserDTO> userNew = userService.findAllUsers();
		
		if (userNew != null) {
			return new ResponseEntity<List<UserDTO>>(userNew, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND);
		}
	}	
}
