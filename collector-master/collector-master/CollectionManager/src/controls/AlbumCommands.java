/*
 * Copyright (c) ...
 */
package controls;

import card.MonsterCard;
import card.SpellCard;
import card.TrapCard;
import gui.AddMonster;
import gui.Album;
import gui.CardCollector;
import gui.CardCollectorLogin;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.DbUtils;


/**
 * The AlbumCommands provides methods for new user registration, login, loading data from database and
 * showing them in the gui table and also provide method for removing chosen card from database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class AlbumCommands {
    
	/* Method addUser is used for new user registration */
    public static void addUser(String name, String password) {        
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `users`(`userName`, `userPassword`) VALUES (?,?)";

        try {
        	preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            if (preparedStatement.executeUpdate() > 0) {
            	JOptionPane.showMessageDialog(null, "Registration succesfull");
            }
        } catch (SQLException ex) {
          	Logger.getLogger(AddMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	}
    }
    
    /* Method login checks if user name and password are right */
    public static void login(String name, String pass) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String username = name; //variable of user name
        String password = pass;	//variable for password
        String query = "SELECT * FROM `users` WHERE `userName` =? AND `userPassword` =?";

        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                CardCollector cardCollector = new CardCollector();
                cardCollector.setVisible(true);
                cardCollector.pack();
                cardCollector.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "NO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardCollectorLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /* Method loadAlbum fetches data from database for every card and displays them in gui table */
    public static void loadAlbum() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM `album`";
        
        try {
            preparedStatement = controls.MyConnection.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            Album.jTableAlbum.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            while(resultSet.next()) {
            	Album.jTableAlbum.setModel(DbUtils.resultSetToTableModel(resultSet));
            }          
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }       
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public static void deleteCard(String cell) {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM `album` WHERE `id` = " + cell; // cell represents table block where card ID is found
        
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            
            loadAlbum();
            JOptionPane.showMessageDialog(null, "Card was removed.");            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
















































































