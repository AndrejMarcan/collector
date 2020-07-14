package com.andy.collector.repository.mongo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "Notes")
public class NoteDaoMongo {
	
	@Indexed
	private int id;
	
	@Field("idCard")
	private int idCard;
	
	@Field("note")
	private String note;
	
	public NoteDaoMongo() {}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	
}
