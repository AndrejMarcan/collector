/*
 * Copyright (c) ...
 */
package com.andy.collector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * The MonsterCard class provides methods for adding a new monster card to database,
 * edit data for monster card in database and to get data for monster card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Entity
public class MonsterCard extends Card {
    
	@Column(name = "type")
	private String type;
	
	@Column(name = "summ_method")
	private String summMethod;	//summoning method
	
	@Column(name = "attribute")
	private String attribute;	//monster attribute	
	
	@Column(name = "attack")
	private String atk;			//attack points
	
	@Column(name = "defense")
	private String def;			//defense points
	
	@Column(name = "level")
	private String level;		//monster level

	public MonsterCard() {};
    
	  public void setLevel(String level) {
	  this.level = level;
	}
	
	public void setAttribute(String atribute) {
	  this.attribute = atribute;
	}
	
	public void setAtk(String atk) {
	  this.atk = atk;
	}
	
	public void setDef(String def) {
	  this.def = def;
	}
	
	public void setSummMethod(String summMethod) {
	  this.summMethod = summMethod;
	}
	
	public String getAttribute() {
	  return attribute;
	}
	
	public String getLevel() {
	  return level;
	}
	
	public String getAtk() {
	  return atk;
	}
	
	public String getDef() {
	  return def;
	}
	
	public String getSummMethod() {
	  return summMethod;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}



















