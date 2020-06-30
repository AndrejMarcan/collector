package com.andy.collector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id")
	@SequenceGenerator(name = "note_id", sequenceName = "note_id")
	private int idNote;
	
	@Column(name = "note")
	private String note;
	
	public Note() {}
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getIdNote() {
		return idNote;
	}

	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}
}
