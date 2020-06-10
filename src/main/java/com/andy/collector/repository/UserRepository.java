/*
 * Copyright (c) ...
 */
package com.andy.collector.repository;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.andy.collector.model.User;


/**
 * The AlbumCommands provides methods for new user registration, login, loading data from database and
 * showing them in the gui table and also provide method for removing chosen card from database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@Repository
public class UserRepository{
    
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	
	//when i used encoder bellow encoding takes longer in addUser method as well as matching in method login
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,15);
	
	/* Method addUser is used for new user registration */
    public User addUser(User user) throws SQLException {
    	final String query = "INSERT INTO users (nickname, password) VALUES (?,?)";
    	jdbcTemplate.update(query, user.getNickname(), encoder.encode(user.getPassword()));
		return user;
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public boolean deleteUser(String cell) throws SQLException {
    	final String query = "DELETE FROM users WHERE id_user = " + cell; // cell represents table block where card ID is found
        
        return jdbcTemplate.update(query) > 0;
    }
    
    /* Method login checks if user name and password are right */
    public boolean login(User user, String id) throws SQLException {
        final String query = "SELECT password FROM users WHERE id_user = " + id;
        
        String rawPass = user.getPassword();        
        String hashedPass = jdbcTemplate.queryForObject(query, String.class);
        
        return encoder.matches(rawPass, hashedPass);
    }
    
}