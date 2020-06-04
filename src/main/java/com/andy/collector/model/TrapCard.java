/*
 * Copyright (c) ...
 */
package main.java.com.andy.collector.model;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import main.java.com.andy.collector.enums.Editions;
import main.java.com.andy.collector.enums.Rarities;

/**
 * The TrapCard class provides methods for adding a new trap card to database, edit data for trap card
 * in database and to get data for trap card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@Component
@Schema(
		type = "object",
		title = "TrapCard"
		)
public class TrapCard extends Card{
	
	@Schema(required = true)
    private String type; //card type
    private final String CARD_TYPE = "trap card";
       
    public TrapCard() {};
    
    /* Constructor for TrapCard class */
    public TrapCard (String name, Rarities rarity, Editions edition, String set,String language,
    				 String type) {
        super(name, rarity, edition, set, language, type);
    }
    
    /* Method shows informations about trap card in console */
    @Override
    public void getInfo() {
        System.out.println(super.getName());
        System.out.println(super.getRarity());
        System.out.println(super.getEdition());
        System.out.println(super.getSet());
        System.out.println(super.getLanguage());
        System.out.println(type);
    }
    
    @Override
    public String getCardType() {
    	return CARD_TYPE;
    }
}








