package com.andy.collector.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.repository.model.Note;

public class NoteTest {
	protected Note note;

	@BeforeEach
	void init() {
		note = new Note();
		
		note.setIdNote(1);
		note.setNote("something");
	}
	
	@Test
	public void testGetMethods() {
		assertEquals(1, note.getIdNote(), "Note id 1 expected!");
		assertEquals("something", note.getNote(), "something expected!");
	}
	
	@Test
	public void testSetMethods() {
		int id = 3;
		String note2 = "new note";
		
		note.setIdNote(id);
		note.setNote(note2);
		
		assertEquals(id, note.getIdNote(), "Note id 3 expected!");
		assertEquals(note2, note.getNote(), "new note expected!");
	}
}
