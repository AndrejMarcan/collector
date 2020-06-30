package com.andy.collector.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

public class CardTest {
	protected SpellCardDTO spell;
	protected NoteDTO note;
	@BeforeEach
	void init() {
		
		note = new NoteDTO();
		note.setNote("Hello from CardTest");
		
		spell = new SpellCardDTO();
		spell.setId(1);
		spell.setName("TestSpell");
		spell.setEdition(Editions.FE);
		spell.setRarity(Rarities.RARE);
		spell.setSet("DDS");
		//spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
	}
	
	@Test
	void testGetMethods() {
		assertEquals("TestSpell", spell.getName(), "TestSpell name expected!");
		assertEquals("First Edition", spell.getEdition(), "First Edition expected!");
		assertEquals("Rare", spell.getRarity(), "Rare expected!");
		assertEquals("DDS", spell.getSet(), "DDS set expected!");
		//assertEquals(note.getNote(), spell.getNote().getNote(), "Hello from CardTest expected!");
		assertEquals("English", spell.getLanguage(), "English language expected!");
	}
	
	@Test
	void testSetMethods() {
		String name = "new name";
		Editions edition = Editions.UE;
		Rarities rarity = Rarities.COM;
		String set = "BLS";
		String language = "German";
		
		NoteDTO note2 = new NoteDTO();
		note2.setNote("new note");
		
		spell.setName(name);
		spell.setEdition(edition);
		spell.setRarity(rarity);
		spell.setSet(set);
		//spell.setNote(note2);
		spell.setLanguage(language);
		
		assertEquals(name, spell.getName(), "new name expected!");
		assertEquals("Unlimited Edition", spell.getEdition(), "Unlimited Edition expected!");
		assertEquals("Common", spell.getRarity(), "Common expected!");
		assertEquals(set, spell.getSet(), "BLS set expected!");
		//assertEquals(note2.getNote(), spell.getNote().getNote(), "new note expected!");
		assertEquals(language, spell.getLanguage(), "German language expected!");
	}
	
	@Test
	public void testGetIdMethod() {
		assertEquals(1, spell.getId(), "ID 1 expected!");
	}
	
	@Test
	public void testSetIdMethod() {
		spell.setId(3);
		assertEquals(3, spell.getId(), "ID 3 expected!");
	}
	
}
