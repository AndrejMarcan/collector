package com.andy.collector.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.model.User;
import com.andy.collector.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	//add new user 
	public void addNewUser(User user) {
		userRepository.save(user);
	}
	
	//update user data by id
	public void updateUserbyId(User user, int id) {
		
		user.setId(id);
		userRepository.save(user);
	}
	
	//delete user by id
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	//get user by id
	public Optional<User> findUser(int id) {
		return userRepository.findById(id);
	}
	
	//get list of all users
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
}
