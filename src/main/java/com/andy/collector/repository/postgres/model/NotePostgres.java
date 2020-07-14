package com.andy.collector.repository.postgres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class NotePostgres {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id")
	@SequenceGenerator(name = "note_id", sequenceName = "note_id")
	private int id;
	
	@Column(name = "idCard")
	private int idCard;
	
	@Column(name = "note")
	private String note;
	
	public NotePostgres() {}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getIdNote() {
		return id;
	}

	public void setIdNote(int idNote) {
		this.id = idNote;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	
	
}
