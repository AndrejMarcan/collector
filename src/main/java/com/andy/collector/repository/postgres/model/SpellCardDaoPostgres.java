package com.andy.collector.repository.postgres.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "spells")
public class SpellCardDaoPostgres extends CardDaoPostgres{
	
	@Column(name = "type")
	@Nonnull
    private String type;	//card type
    
    public SpellCardDaoPostgres() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getCardType() {
		return "Spell Card";
	}
}
