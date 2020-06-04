/*
 * Copyright (c) ...
 */
package com.andy.collector.model;

import org.springframework.stereotype.Component;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * The SpellCard class provides methods for adding a new spell card to database, edit data for spell card
 * in database and to get data for spell card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@Component
@Schema(
		type = "object",
		title = "SpellCard"
		)
public class SpellCard extends Card{
	
	@Schema(required = true)
    private String type;	//card type
    private final String CARD_TYPE = "spell card";
    
    public SpellCard() {}
    
    /* Constructor for SpellCard class */
    public SpellCard (String name, Rarities rarity, Editions edition,String set,
    				  String language, String type) {
        super(name, rarity, edition, set, language, type);
    }
    
    /* Method shows informations about spell card in console */
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









