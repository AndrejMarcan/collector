package com.andy.collector.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andy.collector.dal.Card;
import com.andy.collector.dal.MonsterCard;

public class CardControls  implements DbControls {
	public boolean addCard(MonsterCard monsterCard) throws SQLException {
        String query = "INSERT INTO " + album + "(name, set, edition, language,"
                + "card_type, rarity, type) VALUES (?,?,?,?,?,?,?)";
        String queryDetails = "INSERT INTO " + monsterDetails + "(id_monster, summ_method, attribute, level,"
                + " atk, def ) VALUES ((select max(id) from " + album + "),?,?,?,?,?)";
        String queryNotes = "INSERT INTO " + notes + "(id_card, note) VALUES ((select max(id) from " + album + "), ?)";
        boolean output = false;
        
            try(Connection connection  = MyConnection.getConnection();
            	PreparedStatement preparedStatement = connection.prepareStatement(query);
            	PreparedStatement preparedStatementDetails = connection.prepareStatement(queryDetails);
            	PreparedStatement preparedStatementNotes = connection.prepareStatement(queryNotes);) {
            	
            	connection.setAutoCommit(false);

                try {
                	preparedStatement.setString(1, monsterCard.getName());
                    preparedStatement.setString(2, monsterCard.getSet());
                    preparedStatement.setString(3, monsterCard.getEdition());
                    preparedStatement.setString(4, monsterCard.getLanguage());
                    preparedStatement.setString(5, monsterCard.getCardType());
                    preparedStatement.setString(6, monsterCard.getRarity());
                    preparedStatement.setString(7, monsterCard.getType());
                    preparedStatement.executeUpdate();

                    preparedStatementDetails.setString(1, monsterCard.getSummMethod());
                    preparedStatementDetails.setString(2, monsterCard.getAttribute());
                    preparedStatementDetails.setString(3, monsterCard.getLevel());
                    preparedStatementDetails.setString(4, monsterCard.getAtk());
                    preparedStatementDetails.setString(5, monsterCard.getDef());
                    preparedStatementDetails.executeUpdate();

                    preparedStatementNotes.setString(1, "note");
                    preparedStatementNotes.executeUpdate();

                    output = true;
                } catch (SQLException ex1) {
                	ex1.printStackTrace();
                	
                	throw new SQLException("INSERT unsuccessful !");
                } finally {
                	dbCommit(connection, output);                	
                }
                  
            } catch (SQLException ex) {
            	ex.printStackTrace();
            	throw new SQLException("Card INSERT unsuccessful !");
            } 
            
      return output;
    }
	
    public boolean editCard(MonsterCard monsterCard, String cell) throws SQLException {
        String query = "UPDATE " + album + " SET name = ?, rarity = ?, edition = ?, set = ?, language = ?,"
        				+ " type = ? WHERE id = " + cell;
        String queryDetails = "UPDATE " + monsterDetails + " SET summ_method = ?, attribute = ?, level = ?,"
        		+ " atk = ?, def = ? WHERE id_monster = " + cell;
        boolean output = false;
        
        try (Connection connection  = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        	PreparedStatement preparedStatementDetails = connection.prepareStatement(queryDetails)) {        	
        	connection.setAutoCommit(false);
        	
        	try {
        		preparedStatement.setString(1, monsterCard.getName());
                preparedStatement.setString(2, monsterCard.getRarity());
                preparedStatement.setString(3, monsterCard.getEdition());
                preparedStatement.setString(4, monsterCard.getSet());
                preparedStatement.setString(5, monsterCard.getLanguage());
                preparedStatement.setString(6, monsterCard.getType());
                preparedStatement.executeUpdate(); 
                
                preparedStatementDetails.setString(1, monsterCard.getSummMethod());
                preparedStatementDetails.setString(2, monsterCard.getAttribute());
                preparedStatementDetails.setString(3, monsterCard.getLevel());
                preparedStatementDetails.setString(4, monsterCard.getAtk());
                preparedStatementDetails.setString(5, monsterCard.getDef());
                preparedStatementDetails.executeUpdate();
                output = true;
        	} catch (SQLException ex1) {
            	ex1.printStackTrace();
            	throw new SQLException("UPDATE unsuccessful !");
            } finally {
            	dbCommit(connection, output);                	
            }
        	connection.setAutoCommit(true);
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        	throw new SQLException("Card UPDATE unsuccessful !");
        }
        return output;
    }
    
    public boolean addCard(Card card) throws SQLException{
        String query = "INSERT INTO " + album + "(name, rarity, edition, set,"
                    + " language, type, card_type) VALUES (?,?,?,?,?,?,?)";
        boolean output = false;

        try (Connection connection  = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query); ) {        	
        	connection.setAutoCommit(false);
        	
        	try {
        		preparedStatement.setString(1, card.getName());
                preparedStatement.setString(2, card.getRarity());
                preparedStatement.setString(3, card.getEdition());
                preparedStatement.setString(4, card.getSet());
                preparedStatement.setString(5, card.getLanguage());
                preparedStatement.setString(6, card.getType());
                preparedStatement.setString(7, card.getCardType());

                preparedStatement.executeUpdate();
                output = true;
        	} catch (SQLException ex) {
                ex.printStackTrace();
                throw new SQLException("INSERT unsuccessful !");
            } finally {
            	dbCommit(connection, output);                	
            }
        	connection.setAutoCommit(true);       	
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Card INSERT unsuccessful !");
        } 
        return output;
    }
    
    public boolean editCard(Card card, String cell) throws SQLException{
        String query = "UPDATE " + album + " SET name = ?, rarity = ?, edition = ?, set = ?, language = ?,"
        		+ " type = ? WHERE id = " + cell;
        boolean output = false;
        
        try (Connection connection  = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
        	connection.setAutoCommit(false);
        	
        	try {
        		preparedStatement.setString(1, card.getName());
            	preparedStatement.setString(2, card.getRarity());
            	preparedStatement.setString(3, card.getEdition());
            	preparedStatement.setString(4, card.getSet());
            	preparedStatement.setString(5, card.getLanguage());
            	preparedStatement.setString(6, card.getType());
                preparedStatement.executeUpdate();
                output = true;
        	} catch (SQLException ex) {
                ex.printStackTrace();
                throw new SQLException("UPDATE unsuccessful !");
            } finally {
            	dbCommit(connection, output);                	
            }
        	connection.setAutoCommit(true);
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        	throw new SQLException("Card UPDATE unsuccessful !");
        }
        return output;        
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public boolean deleteCard(String cell) throws SQLException {
        String query = "DELETE FROM " + album + " WHERE id = " + cell; // cell represents table block where card ID is found
        boolean output = false;
        
        try (Connection connection  = MyConnection.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(query); ) {
        	connection.setAutoCommit(false);
        	
        		try {
        			preparedStatement.execute();
                    output = true;		
        		} catch (SQLException ex) {
        			ex.printStackTrace();
        			throw new SQLException("DELETE unsuccessful !");
        		} finally {
        			dbCommit(connection, output);                	
        		}
        	connection.setAutoCommit(true);    
        	
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException("DELETE unsuccessful !");
        } 
        return output;
    }
    
    /* method loadCardDetails collects data from database by card ID and returns ArrayList<String> */
    public ArrayList<String> loadCardDetails(String cell) throws SQLException {
        ArrayList<String> details = new ArrayList<String>();		//creating new ArrayList of Strings
        String query = "SELECT * FROM " + album + " WHERE id = " + cell; 	//cell is table block where card ID is found
        String queryDetails = "SELECT * FROM " + monsterDetails + " WHERE id_monster = " + cell; 
        boolean output = false;
        
        try(Connection connection  = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();) {
        	connection.setAutoCommit(false);
        	
        	try {
        		while(resultSet.next()) {
                    details.add(resultSet.getString("id")); 
                    details.add(resultSet.getString("name"));
                    details.add(resultSet.getString("set"));
                    details.add(resultSet.getString("edition"));
                    details.add(resultSet.getString("language"));
                    details.add(resultSet.getString("card_type")); 
                    details.add(resultSet.getString("rarity"));
                    details.add(resultSet.getString("type"));
                    
                    try(PreparedStatement preparedStatementDetails = connection.prepareStatement(queryDetails);
                    	ResultSet resultSetDetails = preparedStatementDetails.executeQuery(); ) {
                    	while(resultSetDetails.next()) {
    	                	details.add(resultSetDetails.getString("summ_method"));
    	                	details.add(resultSetDetails.getString("attribute"));
    	                	details.add(resultSetDetails.getString("level"));
    	                	details.add(resultSetDetails.getString("atk"));
    	                	details.add(resultSetDetails.getString("def")); 	
    	                    
                    	}
                    }
                }
                output = true;
        	} catch (SQLException ex) {
        		ex.printStackTrace();
        		throw new SQLException("SELECT unsuccessful !");
        	} finally {
        		dbCommit(connection, output);                	
        	}
        	connection.setAutoCommit(true);
            return details;
            
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new SQLException("Card details SELECT unsuccessful !");
        }
    }
    
    /* Method addNotes is used for inserting notes for specific card by card ID */
    public boolean addNotes(String cell, String text) throws SQLException {
        String query = "UPDATE " + notes + " SET note = ? WHERE id_card = " + cell;
        boolean output = false;
        try(Connection connection  = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
        	connection.setAutoCommit(false);
        	
        	try {
                preparedStatement.setString(1, text);		// note text
                preparedStatement.executeUpdate();          
                output = true;    		
        	} catch (SQLException ex) {
        		ex.printStackTrace();
        		throw new SQLException("UPDATE note unsuccessful !");
        	} finally {
        		dbCommit(connection, output);                	
        	}
        	connection.setAutoCommit(true);

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new SQLException("Note text UPDATE unsuccessful !");
        }
        return output;
    }
    
    /* Method loadNotes fetches notes for specific card based on card ID */
    public String loadNotes(String cell) throws SQLException {
        String query = "SELECT " + notes + " FROM notes WHERE id_card = " + cell;
        String notes = "";
        boolean output = false;
        
        try(Connection connection  = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	connection.setAutoCommit(false);
        	
        	try {
        		while(resultSet.next()) {
        			notes = resultSet.getString("note");
        			output = true;
        		}
        		
        	} catch (SQLException ex) {
        		ex.printStackTrace();
        		throw new SQLException("SELECT note unsuccessful !");
        	} finally {
        		dbCommit(connection, output);                	
        	}
        	connection.setAutoCommit(true);
        	return notes;
        	
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new SQLException("Note text SELECT unsuccessful !");
        }
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
