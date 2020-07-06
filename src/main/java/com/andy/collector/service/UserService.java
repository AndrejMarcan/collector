package com.andy.collector.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.UserDTO;
import com.andy.collector.repository.UserRepository;
import com.andy.collector.repository.model.User;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class UserService {
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MapperService mapper;
	
	public UserService() {
		this.encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,15);
	}
	
	//add new user 
	public void addNewUser(UserDTO userDTO) {
		User user = mapper.getUserBoundMapper().map(userDTO);
		String hashPass = encoder.encode(user.getPassword());
		user.setPassword(hashPass);
		
		userRepository.save(user);
	}
	
	//update user data by id
	public void updateUserbyId(UserDTO userDTO, int id) {
	    userDTO.setId(id);
	    User user = mapper.getUserBoundMapper().map(userDTO);
	    
	    String hashPass = encoder.encode(user.getPassword());
	    user.setPassword(hashPass);
	    
	    userRepository.save(user);
	}
	
	//delete user by id
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	//get user by id
	public UserDTO findUser(int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			UserDTO userDTO = mapper.getUserBoundMapper().mapReverse(user.get());
			return userDTO;
		} else {
			return null;
		}
	}
	
	//get list of all users
	public List<UserDTO> findAllUsers(){
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDTO = users.stream().map(p -> mapper.getUserBoundMapper().mapReverse(p)).collect(Collectors.toList());
		
		return usersDTO;
	}
	
	public boolean comparePass(int id, String rawPass) {
		String hashedPass = userRepository.findById(id).get().getPassword();
		return encoder.matches(rawPass, hashedPass);
	}
}
