/*
 * Copyright (c) ...
 */
package controls.db;

import gui.AddMonster;
import gui.Album;
import gui.CardCollector;
import gui.CardCollectorLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * The AlbumCommands provides methods for new user registration, login, loading data from database and
 * showing them in the gui table and also provide method for removing chosen card from database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class AlbumControls {
    
	/* Method addUser is used for new user registration */
    public static void addUser(String name, String password) {        
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        String query = "INSERT INTO `users`(`userName`, `userPassword`) VALUES (?,?)";

        try {
        	connection  = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            if (preparedStatement.executeUpdate() > 0) {
            	//TODO replace JOptionPane..  
            }
        	preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
    	}
    }
    
    /* Method login checks if user name and password are right */
    public static void login(String name, String pass) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String username = name; //variable of user name
        String password = pass;	//variable for password
        String query = "SELECT * FROM `users` WHERE `userName` =? AND `userPassword` =?";

        try {
        	connection  = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                CardCollector cardCollector = new CardCollector();
                cardCollector.setVisible(true);
                cardCollector.pack();
                cardCollector.setLocationRelativeTo(null);
            }
            //TODO replace JOptionPane..  
            resultSet.close();
        	preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
    
    /* Method loadAlbum fetches data from database for every card and displays them in gui table */
    public static void loadAlbum() {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `album`";
        
        try {
        	connection  = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            Album.jTableAlbum.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            while(resultSet.next()) {
            	Album.jTableAlbum.setModel(DbUtils.resultSetToTableModel(resultSet));
            }
          //TODO replace JOptionPane..  
            resultSet.close();
        	preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }       
    }
    
    
}
















































































