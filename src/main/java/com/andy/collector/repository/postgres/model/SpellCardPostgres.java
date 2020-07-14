package com.andy.collector.repository.postgres.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "spells")
public class SpellCardPostgres extends CardPostgres{
	
	@Column(name = "type")
	@Nonnull
    private String type;	//card type
    
    public SpellCardPostgres() {}

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
