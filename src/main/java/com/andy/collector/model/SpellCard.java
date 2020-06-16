/*
 * Copyright (c) ...
 */
package com.andy.collector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * The SpellCard class provides methods for adding a new spell card to database, edit data for spell card
 * in database and to get data for spell card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Entity
public class SpellCard extends Card{
	
	@Column(name = "type")
    private String type;	//card type
	
	@Column(name = "card_type")
    private final String CARD_TYPE = "spell card";
    
    public SpellCard() {}
     
    @Override
    public String getCardType() {
    	return CARD_TYPE;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCARD_TYPE() {
		return CARD_TYPE;
	} 
}









