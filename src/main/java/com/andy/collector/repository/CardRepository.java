package com.andy.collector.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;
import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.SpellCard;
import com.andy.collector.model.TrapCard;


@Repository
public class CardRepository {
		
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Transactional(rollbackFor = Throwable.class)
	public boolean addCard(MonsterCard monsterCard) throws SQLException {
        final String query = "INSERT INTO album(name, set, edition, language,"
                + " card_type, rarity, type) VALUES (?,?,?,?,?,?,?)";
        final String queryDetails = "INSERT INTO monster_details(id_monster, summ_method, attribute, level,"
                + " atk, def ) VALUES ((select max(id) from album),?,?,?,?,?)"; 
        
        jdbcTemplate.update(query, monsterCard.getName(), monsterCard.getSet(),
				monsterCard.getEdition(), monsterCard.getLanguage(), monsterCard.getCardType(),
				monsterCard.getRarity(), monsterCard.getType());
        
        return jdbcTemplate.update(queryDetails, monsterCard.getSummMethod(),
				monsterCard.getAttribute(), monsterCard.getLevel(), monsterCard.getAtk(),
				monsterCard.getDef()) > 0;
    }
	
	@Transactional(rollbackFor = Throwable.class)
    public boolean editCard(MonsterCard monsterCard, String cell) throws SQLException {
    	final String query = "UPDATE album SET name = ?, rarity = ?, edition = ?, set = ?, language = ?,"
        				+ " type = ? WHERE id = " + cell;
    	final String query_details = "UPDATE monster_details SET summ_method = ?, attribute = ?, level = ?,"
        		+ " atk = ?, def = ? WHERE id_monster = " + cell;
        
    	jdbcTemplate.update(query, monsterCard.getName(), monsterCard.getSet(),
				monsterCard.getEdition(), monsterCard.getLanguage(), monsterCard.getCardType(),
				monsterCard.getRarity(), monsterCard.getType());
    	
        return jdbcTemplate.update(query_details, monsterCard.getSummMethod(), monsterCard.getAttribute(),
        							monsterCard.getLevel(), monsterCard.getAtk(), monsterCard.getDef())> 0;
    }
    
    public boolean addCard(Card card) throws SQLException{
    	final String query = "INSERT INTO album(name, rarity, edition, set,"
    				+ " language, type, card_type) VALUES (?,?,?,?,?,?,?)";
    	
    	return jdbcTemplate.update(query, card.getName(), card.getRarity(), card.getEdition(), card.getSet(),
    								card.getLanguage(), card.getType(), card.getCardType())>0;
    }
    
    public boolean editCard(Card card, String cell) throws SQLException{
    	final String query = "UPDATE album SET name = ?, rarity = ?, edition = ?, set = ?, language = ?,"
        		+ " type = ? WHERE id = " + cell;
        
        return jdbcTemplate.update(query, card.getName(), card.getRarity(), card.getEdition(), card.getSet(),
    								card.getLanguage(), card.getType(), card.getCardType())>0;
    			
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public boolean deleteCard(String cell) throws SQLException {
    	final String query = "DELETE FROM album WHERE id = " + cell; // cell represents table block where card ID is found
        
        return jdbcTemplate.update(query) > 0;
    }
    
    /* method loadCardDetails collects data from database by card ID and returns ArrayList<String> */
    public MonsterCard loadMonsterCardDetails(String cell) throws SQLException {
        //ArrayList<String> details = new ArrayList<String>();		//creating new ArrayList of Strings
    	final String query = "SELECT * FROM album WHERE id = " + cell; 	//cell is table block where card ID is found
    	final String queryDetails = "SELECT * FROM monster_details WHERE id_monster = " + cell; 
        
    	MonsterCard card = new MonsterCard();

        List<Map<String, Object>> rowsMonster = jdbcTemplate.queryForList(query);
        List<Map<String, Object>> rowsDetail = jdbcTemplate.queryForList(queryDetails);
	    
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
    }
    
    public List<Card> showAllCards() throws SQLException {
    	final String query = "SELECT * FROM album";

	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);	    
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
	    		Card card = new MonsterCard();
	    		card.setName((String) row.get("name"));
	  	      	card.setRarity(Rarities.rarityPickerLonger((String)row.get("rarity")));
	  	      	card.setEdition(Editions.editionPickerLonger((String)row.get("edition")));
	  	        card.setSet((String) row.get("set"));
	  	        card.setLanguage((String) row.get("language"));
	  	        card.setType((String) row.get("type"));
	  	        result.add(card);
	    	} 	      
	    }
	    
	    return result;
    }
}
