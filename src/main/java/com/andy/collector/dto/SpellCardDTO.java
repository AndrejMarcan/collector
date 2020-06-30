/*
 * Copyright (c) ...
 */
package com.andy.collector.dto;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * The SpellCard class provides methods for adding a new spell card to database, edit data for spell card
 * in database and to get data for spell card from the database.    
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Entity
@Table(name = "spells")
public class SpellCardDTO extends CardDTO{
	
	@Column(name = "type")
	@Nonnull
    private String type;	//card type
    
    public SpellCardDTO() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}








