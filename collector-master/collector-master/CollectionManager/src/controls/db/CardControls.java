package controls.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import card.Card;
import card.MonsterCard;
import card.SpellCard;
import card.TrapCard;

public class CardControls {
    public static void addMonsterCard(MonsterCard monsterCard) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        String query = "INSERT INTO `album`(`name`, `set`, `edition`, `language`,"
                + "`cardType`, `rarity`, `type`,`summMethod`, `attribute`, `level`,"
                + " `atk`, `def` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
            	connection  = MyConnection.getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, monsterCard.getName());
                preparedStatement.setString(2, monsterCard.getSet());
                preparedStatement.setString(3, monsterCard.getEdition());
                preparedStatement.setString(4, monsterCard.getLanguage());
                preparedStatement.setString(5, monsterCard.getCardType());
                preparedStatement.setString(6, monsterCard.getRarity());
                preparedStatement.setString(7, monsterCard.getType());
                preparedStatement.setString(8, monsterCard.getSummMethod());
                preparedStatement.setString(9, monsterCard.getAtribute());
                preparedStatement.setString(10, monsterCard.getLevel());
                preparedStatement.setString(11, monsterCard.getAtk());
                preparedStatement.setString(12, monsterCard.getDef());
                
                if (preparedStatement.executeUpdate() > 0) {
                    //TODO replace JOptionPane..  
                	preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CardControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    
    public static void editMonsterCard(MonsterCard monsterCard, String cell) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        String query = "UPDATE `album` SET `name` ='" + monsterCard.getName() + "'"
                + " , `rarity` ='" + monsterCard.getRarity() + "'"
                + " , `edition` ='" + monsterCard.getEdition() + "'"
                + " , `set`='" + monsterCard.getSet() + "'"
                + " , `language`='" + monsterCard.getLanguage() + "'"
                + " , `type`='" + monsterCard.getType() + "'"
                + " , `summMethod`='" + monsterCard.getSummMethod() + "'"
                + " , `attribute`='" + monsterCard.getAtribute() + "'"
                + " , `level`='" + monsterCard.getLevel() + "'"
                + " , `atk`='" + monsterCard.getAtk() + "'"
                + " , `def`='" + monsterCard.getDef() + "'"
                + " WHERE `id`=" + cell;
        
        try {
        	connection  = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
          //TODO replace JOptionPane..  
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
        	//TODO replace JOptionPane..  
        }
    }
    
    public static void addCard(Card card) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        String query = "INSERT INTO `album`(`name`, `rarity`, `edition`, `set`,"
                    + " `language`, `type`, `cardType`) VALUES (?,?,?,?,?,?,?)";

            try {
            	connection  = MyConnection.getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, card.getName());
                preparedStatement.setString(2, card.getRarity());
                preparedStatement.setString(3, card.getEdition());
                preparedStatement.setString(4, card.getSet());
                preparedStatement.setString(5, card.getLanguage());
                preparedStatement.setString(6, card.getType());
                preparedStatement.setString(7, card.getCardType());

                if (preparedStatement.executeUpdate() > 0) {
                	//TODO replace JOptionPane..  
                    preparedStatement.close();
                    connection.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(CardControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } 
    }
    
    public static void editCard(Card card, String cell) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        String query = "UPDATE `album` SET `name` ='" + card.getName() + "'"
                + ", `rarity` ='" + card.getRarity() + "'"
                + ", `edition` ='" + card.getEdition()+"'"
                + ", `set`='" + card.getSet() + "' "
                + ", `language`='" + card.getLanguage() + "'"
                + ", `type`='" + card.getType() + "'"
                + " WHERE `id`=" + cell;
        try {
        	connection  = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
          //TODO replace JOptionPane..  
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
        	//TODO replace JOptionPane..  
        }
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public static void deleteCard(String cell) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
        String query = "DELETE FROM `album` WHERE `id` = " + cell; // cell represents table block where card ID is found
        
        try {
        	connection  = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
          //TODO replace JOptionPane..  
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
        	//TODO replace JOptionPane..  
        } 
    }
    
    /* method loadCardDetails collects data from database by card ID and returns ArrayList<String> */
    public static ArrayList<String> loadCardDetails(String cell) {
        ArrayList<String> details = new ArrayList<String>();		//creating new ArrayList of Strings
        String query = "SELECT * FROM `album` WHERE `id`="+cell; 	//cell is table block where card ID is found
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
        	connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
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
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
            return details;
        } catch (Exception ex){
            return null;
        }
    }
    
    /* Method addNotes is used for inserting notes for specific card by card ID */
    public static void addNotes(int cardID, String text) {
        String query = "INSERT INTO `notes`(`card_id`, `noteText`) VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
        	connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,cardID);		// card ID 
            preparedStatement.setString(2, text);		// note text
            preparedStatement.execute();
            
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
        	//TODO replace JOptionPane..  
        }
    }
    
    /* Method loadNotes fetches notes for specific card based on card ID */
    public static String loadNotes(String cell) {
        Connection connection = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT `noteText` FROM `notes` WHERE `card_id`="+cell;
        
        try {
        	connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(); 
            String notes = resultSet.getString("noteText");
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(AlbumControls.class.getName()).log(Level.SEVERE, null, ex);   
            return null;
        }
    }
}
