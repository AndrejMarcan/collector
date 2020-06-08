package com.andy.collector.model;

import org.springframework.stereotype.Component;

@Component
public class Note {
	private String id_card;
	private String note;
	
	public Note() {}
	
	public Note(String id_card, String note) {
		this.id_card = id_card;
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getId_card() {
		return id_card;
	}

	public void setIdCard(String id_card) {
		this.id_card = id_card;
	}
	
	
}
