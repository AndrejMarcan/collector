package com.andy.collector.repository.postgres.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "monsters")
public class MonsterCardDaoPostgres extends CardDaoPostgres{
	   
		@Column(name = "type")
		@Nonnull
		private String type;
		
		@Column(name = "summ_method")
		@Nonnull
		private String summMethod;	//summoning method
		
		@Column(name = "attribute")
		@Nonnull
		private String attribute;	//monster attribute	
		
		@Column(name = "attack")
		@Nonnull
		private String atk;			//attack points
		
		@Column(name = "defense")
		@Nonnull
		private String def;			//defense points
		
		@Column(name = "level")
		@Nonnull
		private String level;		//monster level

		public MonsterCardDaoPostgres() {};
	    
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
		
		@Override
		public String getCardType() {
			return "Monster Card";
		}
}
