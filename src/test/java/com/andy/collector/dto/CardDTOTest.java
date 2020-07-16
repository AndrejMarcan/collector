package com.andy.collector.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

public class CardDTOTest {
	protected SpellCardDTO spell;
	protected NoteDTO note;
	protected List<NoteDTO> notes;
	
	@BeforeEach
	void init() {
		
		note = new NoteDTO();
		note.setId(1);
		note.setIdCard(1);
		note.setNote("Hello from CardTest");
		
		notes = new ArrayList<>();
		notes.add(note);
		
		spell = new SpellCardDTO();
		spell.setId(1);
		spell.setName("TestSpell");
		spell.setEdition(Editions.FE);
		spell.setRarity(Rarities.RARE);
		spell.setSet("DDS");
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		spell.setNotes(notes);		
	}
	
	@Test
	void testGetMethods() {
		assertEquals("TestSpell", spell.getName(), "TestSpell name expected!");
		assertEquals("FE", spell.getEdition().toString(), "First Edition expected!");
		assertEquals("RARE", spell.getRarity().toString(), "Rare expected!");
		assertEquals("DDS", spell.getSet(), "DDS set expected!");
		assertEquals("English", spell.getLanguage(), "English language expected!");
		assertEquals(1, spell.getId(), "ID 1 expected!");
	}
	
	@Test
	void testSetMethods() {
		String name = "new name";
		Editions edition = Editions.UE;
		Rarities rarity = Rarities.COM;
		String set = "BLS";
		String language = "German";
		
		spell.setName(name);
		spell.setEdition(edition);
		spell.setRarity(rarity);
		spell.setSet(set);
		spell.setLanguage(language);
		spell.setId(3);
		
		assertEquals(name, spell.getName(), "new name expected!");
		assertEquals("UE", spell.getEdition().toString(), "Unlimited Edition expected!");
		assertEquals("COM", spell.getRarity().toString(), "Common expected!");
		assertEquals(set, spell.getSet(), "BLS set expected!");
		assertEquals(language, spell.getLanguage(), "German language expected!");
		assertEquals(3, spell.getId(), "ID 3 expected!");
	}
}
