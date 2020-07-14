/*
 * Copyright (c) ...
 */
package com.andy.collector.repository.mongo.model;

import javax.annotation.Nonnull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/**
 * The TrapCard class provides methods for adding a new trap card to database, edit data for trap card
 * in database and to get data for trap card from the database.  
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Document(collection = "Cards")
public class TrapCardMongo extends CardMongo{
	
	@Field("type")
    private String type; //card type
    
    public TrapCardMongo() {};
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getCardType() {
		return "Trap Card";
	}
}








