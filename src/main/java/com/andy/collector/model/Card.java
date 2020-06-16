/*
 * Copyright (c) ...
 */
package com.andy.collector.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorFormula;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * The TrapCard class provides methods for adding a new trap card to database, edit data for trap card
 * in database and to get data for trap card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */

@Schema(
		type = "object",
		title = "Card",
		anyOf = { MonsterCard.class, SpellCard.class, TrapCard.class }
)
@Entity
@Table(name = "album")
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorFormula("case when monster_details_id is not null then spellOrtrap else MONSTER end")
public abstract class Card {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
	private int id;
	
	@Schema(required = true)
	@Column(name = "name")
    private String name;		//card name
    
	@Enumerated(EnumType.STRING)
	@Schema(required = true)
	@Column(name = "rarity")
	private Rarities rarity;		//card rarity
	
	@Enumerated(EnumType.STRING)
	@Schema(required = true)
	@Column(name = "edition")
	private Editions edition;		//card edition
	
	@Schema(required = true)
	@Column(name = "set")
	private String set;			//card set
	
	@Schema(required = true)
	@Column(name = "language")
	private String language;	//card language
	
	@Schema(required = true)
	@Column(name = "type")
	private String type;		//card type
    
	@OneToOne(cascade = {CascadeType.ALL})
	private Note note;
	
	public Card() {}

    public abstract String getCardType();
    
    public String getName() {
        return name;
    }
    
    public String getRarity() {
        return rarity.getRarity();
    }
    
    public String getEdition() {
        return edition.getEdition();
    }
    
    public String getSet() {
        return set;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public String getType() {
    	return type;
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

    public void setType(String type) {
    	this.type = type;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
    
    
}

















