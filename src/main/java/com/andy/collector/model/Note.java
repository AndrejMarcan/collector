package com.andy.collector.model;

public class Note {
	private String idCard;
	private String note;
	
	public Note() {}
	
	public Note(String id_card, String note) {
		this.idCard = id_card;
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
}
