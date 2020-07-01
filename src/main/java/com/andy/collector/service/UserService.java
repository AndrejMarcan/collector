package com.andy.collector.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.User;
import com.andy.collector.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,15);
	
	//add new user 
	public void addNewUser(User user) {
		String hashPass = encoder.encode(user.getPassword());
		user.setPassword(hashPass);
		userRepository.save(user);
	}
	
	//update user data by id
	public void updateUserbyId(User user, int id) {
		
		user.setId(id);
		String hashPass = encoder.encode(user.getPassword());
		user.setPassword(hashPass);
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
	
	public boolean comparePass(int id, String rawPass) {
		String hashedPass = userRepository.findById(id).get().getPassword();
		return encoder.matches(rawPass, hashedPass);
	}
}
