package com.andy.collector.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoteDTOTest {
	protected NoteDTO note;

	@BeforeEach
	void init() {
		note = new NoteDTO();
		
		note.setId(1);
		note.setIdCard(1);
		note.setNote("something");
	}
	
	@Test
	public void testGetMethods() {
		assertEquals(1, note.getId(), "Note id 1 expected!");
		assertEquals(1, note.getIdCard(), "Card id 1 expected!");
		assertEquals("something", note.getNote(), "something expected!");
	}
	
	@Test
	public void testSetMethods() {
		int id = 3;
		String note2 = "new note";
		
		note.setId(id);
		note.setIdCard(id);
		note.setNote(note2);
		
		assertEquals(id, note.getId(), "Note id 3 expected!");
		assertEquals(id, note.getIdCard(), "Card id 3 expected!");
		assertEquals(note2, note.getNote(), "new note expected!");
	}
}
