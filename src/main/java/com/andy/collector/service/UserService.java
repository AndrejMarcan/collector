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

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,15);
	
	//add new user 
	public void addNewUser(UserDTO userDTO) {
		mapperFactory.classMap(UserDTO.class, User.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    
	    User user = mapper.map(userDTO, User.class);	    
		String hashPass = encoder.encode(user.getPassword());
		user.setPassword(hashPass);
		
		userRepository.save(user);
	}
	
	//update user data by id
	public void updateUserbyId(UserDTO userDTO, int id) {
		mapperFactory.classMap(UserDTO.class, User.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    userDTO.setId(id);
	    
	    User user = mapper.map(userDTO, User.class);
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
		mapperFactory.classMap(User.class, UserDTO.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			UserDTO userDTO = mapper.map(user.get(),UserDTO.class);
			return userDTO;
		} else {
			return null;
		}
	}
	
	//get list of all users
	public List<UserDTO> findAllUsers(){
		mapperFactory.classMap(User.class, UserDTO.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDTO = users.stream().map(p -> mapper.map(p, UserDTO.class)).collect(Collectors.toList());
		
		return usersDTO;
	}
	
	public boolean comparePass(int id, String rawPass) {
		String hashedPass = userRepository.findById(id).get().getPassword();
		return encoder.matches(rawPass, hashedPass);
	}
}
