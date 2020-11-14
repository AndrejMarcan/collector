package com.andy.collector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.UserDTO;
import com.andy.collector.repository.postgres.UserRepositoryPostgres;
import com.andy.collector.repository.postgres.model.UserPostgres;

import ma.glasnost.orika.MapperFacade;

/**
 * Service class for user objects. 
 * 
 * @version		0.1 14. July 2020
 * @author 		Andrej Marcan
 */

@Service
@CacheConfig(cacheNames = "user_cache")
public class UserService {
	private static final Logger LOG = LoggerFactory.getLogger(CardService.class);
	
	private BCryptPasswordEncoder encoder;
	private MapperFacade mapperPostgres;
	
	@Autowired
	private UserRepositoryPostgres userRepositoryPostgres;

	public UserService(@Autowired MapperService mapperService) {
		this.mapperPostgres = mapperService.getFacadePostgres();
		this.encoder = new BCryptPasswordEncoder();
	}
	
	//add new user 
	public void addNewUser(UserDTO userDTO) {
		LOG.info("Trying to add new user to DB.");
			UserPostgres user = mapperPostgres.map(userDTO, UserPostgres.class);
			String hashPass = encoder.encode(user.getPassword());
			user.setPassword(hashPass);			
			userRepositoryPostgres.save(user);
	}
	
	//update user data by id
	@CacheEvict(key = "#id")
	public void updateUserbyId(UserDTO userDTO, int id) {
		LOG.info("Trying to edit user in DB by its id.");
		
	    userDTO.setId(id);
	    	
	    UserPostgres user = mapperPostgres.map(userDTO, UserPostgres.class);	    
		String hashPass = encoder.encode(user.getPassword());
		user.setPassword(hashPass);	    
		userRepositoryPostgres.save(user);    
	}
	
	//delete user by id
	@CacheEvict(key = "#id")
	public void deleteUser(int id) {
		LOG.info("Trying to delete user by its ID.");
	    userRepositoryPostgres.deleteById(id);
	}
	
	//get user by id
	@Cacheable(key = "#id", unless = "#result == null")
	public UserDTO findUser(int id) {
		LOG.info("Trying to find user by ID {}" + id);
	    UserDTO userDTO = new UserDTO();	
	    Optional<UserPostgres> user = userRepositoryPostgres.findById(id);			
		if(user.isPresent()) {
			userDTO = mapperPostgres.map(user.get(), UserDTO.class);
		} else {
			return null;
		}
		return userDTO;
	}
	
	//get list of all users
	public List<UserDTO> findAllUsers(){
		LOG.info("Trying to get all users from DB.");
		
		List<UserDTO> usersDTO = new ArrayList<>();
	    List<UserPostgres> users = userRepositoryPostgres.findAll();
		usersDTO = users.stream().map(p -> mapperPostgres.map(p, UserDTO.class)).collect(Collectors.toList());
		return usersDTO;
	}
}
