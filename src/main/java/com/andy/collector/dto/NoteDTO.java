package com.andy.collector.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "notes")
public class NoteDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id")
	@SequenceGenerator(name = "note_id", sequenceName = "note_id")
	private int idNote;
	
	@Column(name = "note")
	private String note;
	
	public NoteDTO() {}
	
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
