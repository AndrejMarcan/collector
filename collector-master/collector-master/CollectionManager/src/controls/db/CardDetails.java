/*
 * Copyright (c) ...
 */
package controls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * The CardDetails class provides methods to display card information and allows user to add notes
 * for specific cards.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class CardDetails {
    
	/* method loadCardDetails collects data from database by card ID and returns ArrayList<String> */
    public static ArrayList<String> loadCardDetails(String cell) {
        ArrayList<String> details = new ArrayList<String>();		//creating new ArrayList of Strings
        String query = "SELECT * FROM `album` WHERE `id`="+cell; 	//cell is table block where card ID is found
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                details.add(resultSet.getString("id")); 
                details.add(resultSet.getString("name"));
                details.add(resultSet.getString("set"));
                details.add(resultSet.getString("edition"));
                details.add(resultSet.getString("language"));
                details.add(resultSet.getString("cardType")); 
                details.add(resultSet.getString("rarity"));
                details.add(resultSet.getString("type"));
                details.add(resultSet.getString("summMethod"));
                details.add(resultSet.getString("atribute"));
                details.add(resultSet.getString("level"));
                details.add(resultSet.getString("atk"));
                details.add(resultSet.getString("def"));
            }
            return details;
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
    /* Method addNotes is used for inserting notes for specific card by card ID */
    public static void addNotes(int cardID, String text) {
        String query = "INSERT INTO `notes`(`card_id`, `noteText`) VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,cardID);		// card ID 
            preparedStatement.setString(2, text);		// note text
            preparedStatement.execute();
            
            JOptionPane.showMessageDialog(null, "note saved");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    /* Method loadNotes fetches notes for specific card based on card ID */
    public static String loadNotes(String cell) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `noteText` FROM `notes` WHERE `card_id`="+cell;
        
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(); 
            String notes = resultSet.getString("noteText");
        
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(AlbumCommands.class.getName()).log(Level.SEVERE, null, ex);   
            return null;
        }
    }
}









