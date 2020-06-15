package com.andy.collector.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.model.Card;
import com.andy.collector.model.User;
import com.andy.collector.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public void addNewUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUserbyId(User user, int id) {
		
		user.setId(id);
		userRepository.save(user);
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	public Optional<User> findUser(int id) {
		return userRepository.findById(id);
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
}
