/*
 * Copyright (c) ...
 */
package main.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.gui.Album;

import java.sql.ResultSet;

/**
 * The AlbumCommands provides methods for new user registration, login, loading data from database and
 * showing them in the gui table and also provide method for removing chosen card from database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class AlbumControls implements DbControls{
    
	/* Method addUser is used for new user registration */
    public boolean addUser(String name, String password) throws SQLException {        
        String query = "INSERT INTO users(userName, userPassword) VALUES (?,?)";
        boolean output = false;

        try (Connection connection = MyConnection.getConnection();
        	 PreparedStatement preparedStatement = connection.prepareStatement(query); ) {
        	connection.setAutoCommit(false);
        	
        	try {
        		preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
                output = true;
        	} catch (SQLException ex) {
        		ex.printStackTrace();
        		throw new SQLException("INSERT user unsuccessful !");
        	} finally {
        		dbCommit(connection, output);                	
        	}
        	connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException("User INSERT unsuccessful !");
        } 
        return output;
    }
    
    /* Method login checks if user name and password are right */
    public boolean login(String name, String pass) throws SQLException {
    	
        String username = name; //variable of user name
        String password = pass;	//variable for password
        String query = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
        boolean output = false;
        
        try (Connection connection = MyConnection.getConnection();
        	 PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(); ) {
        	connection.setAutoCommit(false);
        	
        	try {
        		preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                if (resultSet.next()) {
                	output = true;
                }          
        	} catch (SQLException ex) {
        		ex.printStackTrace();
        		throw new SQLException("SELECT user unsuccessful !");
        	} finally {
        		dbCommit(connection, output);                	
        	}
        	connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException("Login SELECT unsuccessful !");
        } 
        return output;
    }
    
    @Override
	public void dbCommit(Connection connection, boolean output) throws SQLException {
		if(connection != null) {
    		if(output) {
        		connection.commit();    
        	} else {
        		connection.rollback();    
        	}
    	}
	}
}
















































































