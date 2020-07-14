/*
 * Copyright (c) ...
 */
package com.andy.collector.repository.mongo.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;
/**
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Document(collection = "Cards")
public abstract class CardMongo {
	
	@Indexed
	private int id;
	
	@Field("name")
    private String name;
	
	@Field("rarity")
	private Rarities rarity;
	
	@Field("edition")
	private Editions edition;
	
	@Field("set")
	private String set;
	
	@Field("language")
	private String language;
	
	@Field("notes")
	private Collection<NoteMongo> notes = new ArrayList<>();
	
	@Field("cardType")
	private String cardType;
	
	public CardMongo() {}
    
    public String getName() {
        return name;
    }
    
    public Rarities getRarity() {
        return rarity;
    }
    
    public Editions getEdition() {
        return edition;
    }
    
    public String getSet() {
        return set;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRarity(Rarities rarity) {
        this.rarity = rarity;
    }
    
    public void setEdition(Editions edition) {
        this.edition = edition;
    }
    
    public void setSet(String set) {
        this.set = set;
    }
    
    public void setLanguage(String language) {
        this.language=language;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<NoteMongo> getNotes() {
		return notes;
	}

	public void setNotes(Collection<NoteMongo> notes) {
		this.notes = notes;
	}

	public abstract String getCardType();
	
    
    
}

















