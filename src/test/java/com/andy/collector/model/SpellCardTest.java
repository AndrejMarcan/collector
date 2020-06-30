package com.andy.collector.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

public class SpellCardTest {
protected static SpellCard spell;
	
	@BeforeEach
	void init() {
		
		Note note = new Note();
		note.setNote("Hello from TrapCardTest");
		
		spell = new SpellCard();
		spell.setName("TestSpell");
		spell.setEdition(Editions.FE);
		spell.setRarity(Rarities.RARE);
		spell.setSet("DDS");
		spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
	}
	
	@Test
	public void testSetTypeMethod() {
		String type = "new type";
		spell.setType(type);
		
		assertEquals(type, spell.getType(), "new type expected.");
	}
	
	@Test
	public void testGetTypeMethod() {
		
		assertEquals("Field Spell Card", spell.getType(),"Field Spell Card expected.");
	}
}
