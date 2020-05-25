/*
 * Copyright (c) ...
 */
package controls.db;

import gui.Album;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.ResultSet;

/**
 * The AlbumCommands provides methods for new user registration, login, loading data from database and
 * showing them in the gui table and also provide method for removing chosen card from database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class AlbumControls {
    
	/* Method addUser is used for new user registration */
    public static boolean addUser(String name, String password) throws SQLException {        
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
    public static boolean login(String name, String pass) throws SQLException {
    	
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
    
    /* Method loadAlbum fetches data from database for every card and displays them in gui table */
    public static boolean loadAlbum() throws SQLException { //TODO need some editing remove parts with jTable
        String query = "SELECT * FROM album";
        boolean output = false;
        try (Connection connection = MyConnection.getConnection();
        	 PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(); ) {
        	connection.setAutoCommit(false);
        	
        	try {
                Album.jTableAlbum.setModel(DbUtils.resultSetToTableModel(resultSet)); 
                while(resultSet.next()) {
                	Album.jTableAlbum.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
                output = true;		
        	} catch (SQLException ex) {
        		ex.printStackTrace();
        		throw new SQLException("SELECT unsuccessful !");
        	} finally {
        		dbCommit(connection, output);                	
        	}
        	connection.setAutoCommit(true);
        	
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException("Abum SELECT unsuccessful !");
        } 
        return output;
    }    
    
    private static void dbCommit(Connection connection, Boolean output) throws SQLException {
		if(connection != null) {
    		if(output) {
        		connection.commit();    
        	} else {
        		connection.rollback();    
        	}
    	}
	}
}
















































































