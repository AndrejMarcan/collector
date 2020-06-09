package com.andy.collector.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;
import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.Note;
import com.andy.collector.model.SpellCard;
import com.andy.collector.model.TrapCard;


@Repository
public class CardRepository  extends DbRepository {
	
//	@Autowired
//	MyConnection myConnection;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//How to make these two methods do queries in transaction???
	
	@Transactional
	public boolean addCard(MonsterCard monsterCard) throws SQLException {
        final String QUERY = "INSERT INTO " + album + "(name, set, edition, language,"
                + " card_type, rarity, type) VALUES (?,?,?,?,?,?,?)";
        final String QUERY_DETAILS = "INSERT INTO " + monsterDetails + "(id_monster, summ_method, attribute, level,"
                + " atk, def ) VALUES ((select max(id) from " + album + "),?,?,?,?,?)";
        final String QUERY_NOTES = "INSERT INTO " + notes + "(id_card, note) VALUES ((select max(id) from " + album + "), ?)";

        jdbcTemplate.update(QUERY, monsterCard.getName(), monsterCard.getSet(), monsterCard.getEdition(), monsterCard.getLanguage(),
        						monsterCard.getCardType(), monsterCard.getRarity(), monsterCard.getType());
        
        jdbcTemplate.update(QUERY_DETAILS, monsterCard.getSummMethod(), monsterCard.getAttribute(), monsterCard.getLevel(),
        						monsterCard.getAtk(), monsterCard.getDef());
        
        return jdbcTemplate.update(QUERY_NOTES, "note") > 0;

        
//        boolean output = false;
//        
//            try(Connection connection  = myConnection.getConnection();
//            	PreparedStatement preparedStatement = connection.prepareStatement(query);
//            	PreparedStatement preparedStatementDetails = connection.prepareStatement(queryDetails);
//            	PreparedStatement preparedStatementNotes = connection.prepareStatement(queryNotes);) {
//            	
//            	connection.setAutoCommit(false);
//
//                try {
//                	preparedStatement.setString(1, monsterCard.getName());
//                    preparedStatement.setString(2, monsterCard.getSet());
//                    preparedStatement.setString(3, monsterCard.getEdition());
//                    preparedStatement.setString(4, monsterCard.getLanguage());
//                    preparedStatement.setString(5, monsterCard.getCardType());
//                    preparedStatement.setString(6, monsterCard.getRarity());
//                    preparedStatement.setString(7, monsterCard.getType());
//                    preparedStatement.executeUpdate();
//
//                    preparedStatementDetails.setString(1, monsterCard.getSummMethod());
//                    preparedStatementDetails.setString(2, monsterCard.getAttribute());
//                    preparedStatementDetails.setString(3, monsterCard.getLevel());
//                    preparedStatementDetails.setString(4, monsterCard.getAtk());
//                    preparedStatementDetails.setString(5, monsterCard.getDef());
//                    preparedStatementDetails.executeUpdate();
//
//                    preparedStatementNotes.setString(1, "note");
//                    preparedStatementNotes.executeUpdate();
//
//                    output = true;
//                } catch (SQLException ex1) {
//                	ex1.printStackTrace();
//                	
//                	throw new SQLException("INSERT unsuccessful !");
//                } finally {
//                	dbCommit(connection, output);                	
//                }
//                  
//            } catch (SQLException ex) {
//            	ex.printStackTrace();
//            	throw new SQLException("Card INSERT unsuccessful !");
//            } 
//            
//      return output;
    }
	
    public boolean editCard(MonsterCard monsterCard, String cell) throws SQLException {
    	final String QUERY = "UPDATE " + album + " SET name = ?, rarity = ?, edition = ?, set = ?, language = ?,"
        				+ " type = ? WHERE id = " + cell;
    	final String QUERY_DETAILS = "UPDATE " + monsterDetails + " SET summ_method = ?, attribute = ?, level = ?,"
        		+ " atk = ?, def = ? WHERE id_monster = " + cell;
        
        jdbcTemplate.update(QUERY, monsterCard.getName(), monsterCard.getRarity(), monsterCard.getEdition(), monsterCard.getSet(),
        		monsterCard.getLanguage(), monsterCard.getType());
        
        return jdbcTemplate.update(QUERY_DETAILS, monsterCard.getSummMethod(), monsterCard.getAttribute(), monsterCard.getLevel(),
				monsterCard.getAtk(), monsterCard.getDef()) > 0;
//        boolean output = false;
//        
//        try (Connection connection  = myConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//        	PreparedStatement preparedStatementDetails = connection.prepareStatement(queryDetails)) {        	
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		preparedStatement.setString(1, monsterCard.getName());
//                preparedStatement.setString(2, monsterCard.getRarity());
//                preparedStatement.setString(3, monsterCard.getEdition());
//                preparedStatement.setString(4, monsterCard.getSet());
//                preparedStatement.setString(5, monsterCard.getLanguage());
//                preparedStatement.setString(6, monsterCard.getType());
//                preparedStatement.executeUpdate(); 
//                
//                preparedStatementDetails.setString(1, monsterCard.getSummMethod());
//                preparedStatementDetails.setString(2, monsterCard.getAttribute());
//                preparedStatementDetails.setString(3, monsterCard.getLevel());
//                preparedStatementDetails.setString(4, monsterCard.getAtk());
//                preparedStatementDetails.setString(5, monsterCard.getDef());
//                preparedStatementDetails.executeUpdate();
//                output = true;
//        	} catch (SQLException ex1) {
//            	ex1.printStackTrace();
//            	throw new SQLException("UPDATE unsuccessful !");
//            } finally {
//            	dbCommit(connection, output);                	
//            }
//        	connection.setAutoCommit(true);
//        	
//        } catch (Exception ex) {
//        	ex.printStackTrace();
//        	throw new SQLException("Card UPDATE unsuccessful !");
//        }
//        return output;
    }
    
    public boolean addCard(Card card) throws SQLException{
    	
    	final String QUERY = "INSERT INTO " + album + "(name, rarity, edition, set,"
    				+ " language, type, card_type) VALUES (?,?,?,?,?,?,?)";
    	
    	return jdbcTemplate.update(QUERY, card.getName(), card.getRarity(), card.getEdition(), card.getSet(),
    			card.getLanguage(), card.getType(), card.getCardType())>0;
    	
//        String query = "INSERT INTO " + album + "(name, rarity, edition, set,"
//                    + " language, type, card_type) VALUES (?,?,?,?,?,?,?)";
//        boolean output = false;
//
//        try (Connection connection  = myConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query); ) {        	
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		preparedStatement.setString(1, card.getName());
//                preparedStatement.setString(2, card.getRarity());
//                preparedStatement.setString(3, card.getEdition());
//                preparedStatement.setString(4, card.getSet());
//                preparedStatement.setString(5, card.getLanguage());
//                preparedStatement.setString(6, card.getType());
//                preparedStatement.setString(7, card.getCardType());
//
//                preparedStatement.executeUpdate();
//                output = true;
//        	} catch (SQLException ex) {
//                ex.printStackTrace();
//                throw new SQLException("INSERT unsuccessful !");
//            } finally {
//            	dbCommit(connection, output);                	
//            }
//        	connection.setAutoCommit(true);       	
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw new SQLException("Card INSERT unsuccessful !");
//        } 
//        return output;
    }
    
    public boolean editCard(Card card, String cell) throws SQLException{
    	final String QUERY = "UPDATE " + album + " SET name = ?, rarity = ?, edition = ?, set = ?, language = ?,"
        		+ " type = ? WHERE id = " + cell;
        
        return jdbcTemplate.update(QUERY, card.getName(), card.getRarity(), card.getEdition(), card.getSet(),
    			card.getLanguage(), card.getType(), card.getCardType())>0;
    			
//        boolean output = false;
//        
//        try (Connection connection  = myConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		preparedStatement.setString(1, card.getName());
//            	preparedStatement.setString(2, card.getRarity());
//            	preparedStatement.setString(3, card.getEdition());
//            	preparedStatement.setString(4, card.getSet());
//            	preparedStatement.setString(5, card.getLanguage());
//            	preparedStatement.setString(6, card.getType());
//                preparedStatement.executeUpdate();
//                output = true;
//        	} catch (SQLException ex) {
//                ex.printStackTrace();
//                throw new SQLException("UPDATE unsuccessful !");
//            } finally {
//            	dbCommit(connection, output);                	
//            }
//        	connection.setAutoCommit(true);
//        	
//        } catch (Exception ex) {
//        	ex.printStackTrace();
//        	throw new SQLException("Card UPDATE unsuccessful !");
//        }
//        return output;        
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public boolean deleteCard(String cell) throws SQLException {
    	final String QUERY = "DELETE FROM " + album + " WHERE id = " + cell; // cell represents table block where card ID is found
        
        return jdbcTemplate.update(QUERY) > 0;
        
//        boolean output = false;
//        
//        try (Connection connection  = myConnection.getConnection();
//        	PreparedStatement preparedStatement = connection.prepareStatement(query); ) {
//        	connection.setAutoCommit(false);
//        	
//        		try {
//        			preparedStatement.execute();
//                    output = true;		
//        		} catch (SQLException ex) {
//        			ex.printStackTrace();
//        			throw new SQLException("DELETE unsuccessful !");
//        		} finally {
//        			dbCommit(connection, output);                	
//        		}
//        	connection.setAutoCommit(true);    
//        	
//        } catch (SQLException ex) {
//        	ex.printStackTrace();
//        	throw new SQLException("DELETE unsuccessful !");
//        } 
//        return output;
    }
    
    /* method loadCardDetails collects data from database by card ID and returns ArrayList<String> */
    public MonsterCard loadMonsterCardDetails(String cell) throws SQLException {
        //ArrayList<String> details = new ArrayList<String>();		//creating new ArrayList of Strings
    	final String QUERY = "SELECT * FROM " + album + " WHERE id = " + cell; 	//cell is table block where card ID is found
    	final String QUERY_DETAILS = "SELECT * FROM " + monsterDetails + " WHERE id_monster = " + cell; 
        
    	MonsterCard card = new MonsterCard();
    	
        List<Map<String, Object>> rowsMonster = jdbcTemplate.queryForList(QUERY);
        List<Map<String, Object>> rowsDetail = jdbcTemplate.queryForList(QUERY_DETAILS);
	    
        for(Map<String, Object> row:rowsMonster) {
	  	    card.setName((String) row.get("name"));
	  	    card.setRarity(Rarities.rarityPickerLonger((String)row.get("rarity")));
	  	    card.setEdition(Editions.editionPickerLonger((String)row.get("edition")));
	  	    card.setSet((String) row.get("set"));
	  	    card.setLanguage((String) row.get("language"));
	  	    card.setType((String) row.get("type"));
	  	}
        
        for(Map<String, Object> row:rowsDetail) {
        	card.setSummMethod((String) row.get("summ_method"));
        	card.setAttribute((String) row.get("attribute"));
        	card.setLevel((String) row.get("level"));
        	card.setAtk((String) row.get("atk"));
        	card.setDef((String) row.get("def"));
        }
        
        return card;
        
//        boolean output = false;
//        
//        try(Connection connection  = myConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();) {
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		while(resultSet.next()) {
//                    details.add(resultSet.getString("id")); 
//                    details.add(resultSet.getString("name"));
//                    details.add(resultSet.getString("set"));
//                    details.add(resultSet.getString("edition"));
//                    details.add(resultSet.getString("language"));
//                    details.add(resultSet.getString("card_type")); 
//                    details.add(resultSet.getString("rarity"));
//                    details.add(resultSet.getString("type"));
//                    
//                    try(PreparedStatement preparedStatementDetails = connection.prepareStatement(queryDetails);
//                    	ResultSet resultSetDetails = preparedStatementDetails.executeQuery(); ) {
//                    	while(resultSetDetails.next()) {
//    	                	details.add(resultSetDetails.getString("summ_method"));
//    	                	details.add(resultSetDetails.getString("attribute"));
//    	                	details.add(resultSetDetails.getString("level"));
//    	                	details.add(resultSetDetails.getString("atk"));
//    	                	details.add(resultSetDetails.getString("def")); 	  	                    
//                    	}
//                    }
//                }
//                output = true;
//        	} catch (SQLException ex) {
//        		ex.printStackTrace();
//        		throw new SQLException("SELECT unsuccessful !");
//        	} finally {
//        		dbCommit(connection, output);                	
//        	}
//        	connection.setAutoCommit(true);
//        	Rarities rarity = Rarities.rarityPickerLonger(resultSet.getString("rarity"));              	
//        	Editions edition  = Editions.editionPickerLonger(resultSet.getString("edition"));       
//        	MonsterCard monsterCard = new MonsterCard(details.get(1), rarity, edition, details.get(2),
//        									details.get(4), details.get(7), details.get(8), details.get(9),
//        									details.get(10), details.get(11), details.get(12));
//        	
//            return monsterCard;
//            
//        } catch (SQLException ex){
//            ex.printStackTrace();
//            throw new SQLException("Card details SELECT unsuccessful !");
//        }
    }
    
    public List<Card> showAllCards() throws SQLException {
    	final String QUERY = "SELECT * FROM album";

	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(QUERY);	    
	    List<Card> result = new ArrayList<Card>();
	    
	    for(Map<String, Object> row:rows){
	    	if(row.get("card_type").equals("spell card")){
	    		Card card = new SpellCard();
	  	      	card.setName((String) row.get("name"));
	  	      	card.setRarity(Rarities.rarityPickerLonger((String)row.get("rarity")));
	  	      	card.setEdition(Editions.editionPickerLonger((String)row.get("edition")));
	  	        card.setSet((String) row.get("set"));
	  	        card.setLanguage((String) row.get("language"));
	  	        card.setType((String) row.get("type"));
	  	      	result.add(card);
	    	} if (row.get("card_type").equals("trap card")){
	    		Card card = new TrapCard();
	  	      	card.setName((String) row.get("name"));
	  	      	card.setRarity(Rarities.rarityPickerLonger((String)row.get("rarity")));
	  	      	card.setEdition(Editions.editionPickerLonger((String)row.get("edition")));
	  	        card.setSet((String) row.get("set"));
	  	        card.setLanguage((String) row.get("language"));
	  	        card.setType((String) row.get("type"));
	  	      	result.add(card);
	    	} if (row.get("card_type").equals("monster card")){
	    		//Card card = loadMonsterCardDetails("id");
	  	      	//result.add(card);
	    	} 
	      
	    }
	    
	    return result;
        
//		  boolean output = false;
//        ArrayList<Card> list = new ArrayList<>();
//        try (Connection connection = myConnection.getConnection();
//        	 PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery(); ) {
//        	connection.setAutoCommit(false);
//        	
//        	try {               
//                while(resultSet.next()) {
//                	if(resultSet.getString("card_type").equals("spell card")) {                	
//                	Rarities rarity = Rarities.rarityPickerLonger(resultSet.getString("rarity"));              	
//                	Editions edition  = Editions.editionPickerLonger(resultSet.getString("edition"));              	
//                	SpellCard card = new SpellCard (resultSet.getString("name"), rarity
//                			, edition, resultSet.getString("set")
//                			, resultSet.getString("language"), resultSet.getString("type"));
//                	list.add(card);
//                	}
//                	if(resultSet.getString("card_type").equals("trap card")) {                	
//                		Rarities rarity = Rarities.rarityPickerLonger(resultSet.getString("rarity"));              	
//                		Editions edition  = Editions.editionPickerLonger(resultSet.getString("edition"));              	
//                    	Card card = new TrapCard (resultSet.getString("name"), rarity
//                    			, edition, resultSet.getString("set")
//                    			, resultSet.getString("language"), resultSet.getString("type"));
//                    	list.add(card);
//                    	}
////                	if(resultSet.getString("card_type").equals("monster card")) {                	
////                    	Card card = loadMonsterCardDetails(resultSet.getString("id"));
////                    	list.add(card);
////                    	}
//                }
//                output = true;		
//        	} catch (SQLException ex) {
//        		ex.printStackTrace();
//        		throw new SQLException("SELECT unsuccessful !");
//        	} finally {
//        		dbCommit(connection, output);                	
//        	}
//        	connection.setAutoCommit(true);
//        	
//        } catch (SQLException ex) {
//        	ex.printStackTrace();
//        	throw new SQLException("Abum SELECT unsuccessful !");
//        } 
//        return list;
    }
    
    /* Method addNotes is used for inserting notes for specific card by card ID */
    public boolean addNotes(String cell, Note note) throws SQLException {
    	final String QUERY = "UPDATE " + notes + " SET note = '?' WHERE id_card = " + cell;  
        
        return jdbcTemplate.update(QUERY, note.getNote())>0;
        
//        boolean output = false;
//        try(Connection connection  = myConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
//        	connection.setAutoCommit(false);
//        	
//        	try {
//                preparedStatement.setString(1, text);		// note text
//                preparedStatement.executeUpdate();          
//                output = true;    		
//        	} catch (SQLException ex) {
//        		ex.printStackTrace();
//        		throw new SQLException("UPDATE note unsuccessful !");
//        	} finally {
//        		dbCommit(connection, output);                	
//        	}
//        	connection.setAutoCommit(true);
//
//        } catch (SQLException ex){
//            ex.printStackTrace();
//            throw new SQLException("Note text UPDATE unsuccessful !");
//        }
//        return output;
    }
    
    /* Method loadNotes fetches notes for specific card based on card ID */
    public String loadNotes(String cell) throws SQLException {
    	final String QUERY = "SELECT * FROM notes WHERE id_card = " + cell;
        Note note = new Note();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(QUERY);
        for(Map<String, Object> row:rows) {
	  	    note.setNote((String) row.get("note"));    
	  	}
        return note.getNote();
//        boolean output = false;
//        
//        try(Connection connection  = myConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//        	ResultSet resultSet = preparedStatement.executeQuery();) {
//        	connection.setAutoCommit(false);
//        	
//        	try {
//        		while(resultSet.next()) {
//        			notes = resultSet.getString("note");
//        			output = true;
//        		}
//        		
//        	} catch (SQLException ex) {
//        		ex.printStackTrace();
//        		throw new SQLException("SELECT note unsuccessful !");
//        	} finally {
//        		dbCommit(connection, output);                	
//        	}
//        	connection.setAutoCommit(true);
//        	return notes;
//        	
//        } catch (SQLException ex){
//            ex.printStackTrace();
//            throw new SQLException("Note text SELECT unsuccessful !");
//        }
    }
}
