package com.andy.collector.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

public class TrapCardTest {
	protected static TrapCardDTO trap;
	
	@BeforeEach
	void init() {
		
		NoteDTO note = new NoteDTO();
		note.setNote("Hello from TrapCardTest");
		
		trap = new TrapCardDTO();
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		//trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
	}
	
	@Test
	public void testSetTypeMethod() {
		String type = "new type";
		trap.setType(type);
		
		assertEquals(type, trap.getType(), "new type expected.");
	}
	
	@Test
	public void testGetTypeMethod() {
		
		assertEquals("Counter Trap Card", trap.getType(),"Counter Trap Card expected.");
	}
}
