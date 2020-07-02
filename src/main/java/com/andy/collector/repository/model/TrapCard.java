/*
 * Copyright (c) ...
 */
package com.andy.collector.repository.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * The TrapCard class provides methods for adding a new trap card to database, edit data for trap card
 * in database and to get data for trap card from the database.  
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Entity
@Table(name = "traps")
public class TrapCard extends Card{
	
	@Column(name = "type")
	@Nonnull
    private String type; //card type
       
    public TrapCard() {};
    
    
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








