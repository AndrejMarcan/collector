/*
 * Copyright (c) ...
 */
package com.andy.collector.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.andy.collector.model.User;

import java.sql.ResultSet;

/**
 * The AlbumCommands provides methods for new user registration, login, loading data from database and
 * showing them in the gui table and also provide method for removing chosen card from database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@Repository
public class UserRepository extends DbRepository{
    
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void createTableUsers() {
		final String QUERY = "CREATE TABLE IF NOT EXISTS public.users (id_user bigint NOT NULL GENERATED ALWAYS AS IDENTITY "
				+ "( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 987654321 ),"
				+ " nickname text NOT NULL, password text NOT NULL,"
				+ " PRIMARY KEY (id_user) );"
				+ "ALTER TABLE public.album OWNER to postgres;";
		jdbcTemplate.execute(QUERY);
	}
	
	/* Method addUser is used for new user registration */
    public User addUser(User user) throws SQLException {
    	final String QUERY = "INSERT INTO users (nickname, password) VALUES (?,?)";
    	jdbcTemplate.update(QUERY, user.getNickname(), user.getPassword());
		return user;
		
//        String query = "INSERT INTO users (userName, userPassword) VALUES (?,?)";
//        boolean output = false;
//
//        try (Connection connection = myConnection.getConnection();
//        	 PreparedStatement preparedStatement = connection.prepareStatement(query); ) {
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		preparedStatement.setString(1, name);
//                preparedStatement.setString(2, password);
//                preparedStatement.executeUpdate();
//                output = true;
//        	} catch (SQLException ex) {
//        		ex.printStackTrace();
//        		throw new SQLException("INSERT user unsuccessful !");
//        	} finally {
//        		dbCommit(connection, output);                	
//        	}
//        	connection.setAutoCommit(true);
//            
//        } catch (SQLException ex) {
//        	ex.printStackTrace();
//        	throw new SQLException("User INSERT unsuccessful !");
//        } 
//        return output;
   }
    
    /* Method login checks if user name and password are right */
    public User login(String name, String pass) throws SQLException {
        final String QUERY = "SELECT * FROM users WHERE nickname = "+ name +" AND password = "+ pass +"";       
        
        User user = jdbcTemplate.queryForObject(QUERY, (rs, rowNum) -> new User(rs.getString("nickname"),rs.getString("password")));
        
        return user;
//        try (Connection connection = myConnection.getConnection();
//        	 PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery(); ) {
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
//
//                if (resultSet.next()) {
//                	output = true;
//                }          
//        	} catch (SQLException ex) {
//        		ex.printStackTrace();
//        		throw new SQLException("SELECT user unsuccessful !");
//        	} finally {
//        		dbCommit(connection, output);                	
//        	}
//        	connection.setAutoCommit(true);
//            
//        } catch (SQLException ex) {
//        	ex.printStackTrace();
//        	throw new SQLException("Login SELECT unsuccessful !");
//        } 
//        return output;
    }
}