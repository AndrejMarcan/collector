package com.andy.collector.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MonsterDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Schema(required = true)
    private String summMethod;	//summoning method
    
	@Schema(required = true)
	private String attribute;	//monster attribute
	
	@Schema(required = true)
	private String atk;			//attack points
    
	@Schema(required = true)
	private String def;			//defense points
    
	@Schema(required = true)
	private String level;		//monster level

	public String getSummMethod() {
		return summMethod;
	}

	public void setSummMethod(String summMethod) {
		this.summMethod = summMethod;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAtk() {
		return atk;
	}

	public void setAtk(String atk) {
		this.atk = atk;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
