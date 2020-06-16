/*
 * Copyright (c) ...
 */
package com.andy.collector.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * The TrapCard class provides methods for adding a new trap card to database, edit data for trap card
 * in database and to get data for trap card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Entity
//@DiscriminatorValue("TRAP CARD")
public class TrapCard extends Card{
	
	@Column(name = "type")
    private String type; //card type
	
	@Column(name = "card_type")
    private final String CARD_TYPE = "trap card";
       
    public TrapCard() {};
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCARD_TYPE() {
		return CARD_TYPE;
	}

	@Override
    public String getCardType() {
    	return CARD_TYPE;
    }
}








