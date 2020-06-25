package com.andy.collector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
public class Note {
	
	@Id
	@GeneratedValue(generator = "Note_SequenceStyleGenerator")
	@GenericGenerator(name = "Note_SequenceStyleGenerator", 
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	parameters = {
		@Parameter(name = "sequence_name", value = "Note_SEQ"),
		@Parameter(name = "initial_value", value = "1"),
		@Parameter(name = "increment_size", value = "1") 
	})
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
