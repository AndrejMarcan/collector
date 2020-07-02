package com.andy.collector.dto;

public class NoteDTO {
	private int idNote;
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
