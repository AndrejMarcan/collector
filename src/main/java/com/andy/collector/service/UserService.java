package com.andy.collector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andy.collector.Main;
import com.andy.collector.dto.UserDTO;
import com.andy.collector.repository.mongo.UserRepositoryMongo;
import com.andy.collector.repository.mongo.model.UserMongo;
import com.andy.collector.repository.postgres.UserRepositoryPostgres;
import com.andy.collector.repository.postgres.model.UserPostgres;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;

/**
 * Service class for user objects. 
 * 
 * @version		0.1 14. July 2020
 * @author 		Andrej Marcan
 */

@Service
public class UserService {
	
	@Value("${andy.database.picker}")
	private String layer; //try something else...
	private BCryptPasswordEncoder encoder;
	private MapperFacade mapperMongo;
	private MapperFacade mapperPostgres;
	
	@Autowired
	private UserRepositoryMongo userRepositoryMongo;
	
	@Autowired
	private UserRepositoryPostgres userRepositoryPostgres;

	public UserService(@Autowired MapperService mapperService) {
		this.mapperMongo = mapperService.getFacadeMongo();
		this.mapperPostgres = mapperService.getFacadePostgres();
		this.encoder = new BCryptPasswordEncoder();
	}
	
	//add new user 
	public void addNewUser(UserDTO userDTO) {
		if(layer.equals("mongo")) {
			
			UserMongo user = mapperMongo.map(userDTO, UserMongo.class);
			String hashPass = encoder.encode(user.getPassword());
			user.setPassword(hashPass);			
			userRepositoryMongo.save(user);
			
		} else if (layer.equals("postgres")) {
			
			UserPostgres user = mapperPostgres.map(userDTO, UserPostgres.class);
			String hashPass = encoder.encode(user.getPassword());
			user.setPassword(hashPass);			
			userRepositoryPostgres.save(user);
		}
	}
	
	//update user data by id
	public void updateUserbyId(UserDTO userDTO, int id) {
	    userDTO.setId(id);
	    
	    if(layer.equals("mongo")){
	    	
	    	UserMongo user = mapperMongo.map(userDTO, UserMongo.class);	    
		    String hashPass = encoder.encode(user.getPassword());
		    user.setPassword(hashPass);	    
		    userRepositoryMongo.save(user);
		    
	    } else if(layer.equals("postgres")) {
	    	
	    	UserPostgres user = mapperPostgres.map(userDTO, UserPostgres.class);	    
		    String hashPass = encoder.encode(user.getPassword());
		    user.setPassword(hashPass);	    
		    userRepositoryPostgres.save(user);
	    }	    
	}
	
	//delete user by id
	public void deleteUser(int id) {
		if(layer.equals("mongo")){
			
			userRepositoryMongo.deleteById(id);
			
	    } else if(layer.equals("postgres")) {
	    	
	    	userRepositoryPostgres.deleteById(id);
	    }
	}
	
	//get user by id
	public UserDTO findUser(int id) {
		UserDTO userDTO = null;
		if(layer.equals("mongo")){
			
			Optional<UserMongo> user = userRepositoryMongo.findById(id);		
			if(user.isPresent()) {
				userDTO = mapperMongo.map(user.get(), UserDTO.class);
			} else {
				return null;
			}
			
	    } else if(layer.equals("postgres")) {
	    	
	    	Optional<UserPostgres> user = userRepositoryPostgres.findById(id);			
			if(user.isPresent()) {
				userDTO = mapperPostgres.map(user.get(), UserDTO.class);
			} else {
				return null;
			}
	    }
		return userDTO;
	}
	
	//get list of all users
	public List<UserDTO> findAllUsers(){
		List<UserDTO> usersDTO = new ArrayList<>();
		if(layer.equals("mongo")){
			
			List<UserMongo> users = userRepositoryMongo.findAll();
			usersDTO = users.stream().map(p -> mapperMongo.map(p, UserDTO.class)).collect(Collectors.toList());
			
	    } else if(layer.equals("postgres")) {
	    	
	    	List<UserPostgres> users = userRepositoryPostgres.findAll();
			usersDTO = users.stream().map(p -> mapperPostgres.map(p, UserDTO.class)).collect(Collectors.toList());
	    }		
		return usersDTO;
	}
	
	public boolean comparePass(int id, String rawPass) {
		String hashedPass = userRepositoryMongo.findById(id).get().getPassword();
		return encoder.matches(rawPass, hashedPass);
	}
}
