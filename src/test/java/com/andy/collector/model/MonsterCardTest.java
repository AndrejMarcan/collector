package com.andy.collector.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

public class MonsterCardTest {
	protected MonsterCard monster;
	
	@BeforeEach
	void init() {
		Note note = new Note();
		note.setNote("some note");
		
		monster = new MonsterCard();
		monster.setName("TestMonster");
		monster.setEdition(Editions.LE);
		monster.setRarity(Rarities.GHOST);
		monster.setSet("DDS");
		//monster.setNote(note);
		monster.setLanguage("English");
		monster.setType("Warrior");
		monster.setSummMethod("Fusion");
		monster.setAttribute("Water");
		monster.setAtk("2300");
		monster.setDef("XXXX");
		monster.setLevel("7");
	}
	
	@Test
	public void testSetTypeMethod() {
		String type = "new type";
		monster.setType(type);
		assertEquals(type, monster.getType(), "new type expected.");
	}
	
	@Test
	public void testSetSummMethodMethod() {
		String method = "Synchro";
		monster.setSummMethod(method);
		assertEquals(method, monster.getSummMethod(), "Synchro summoning method expected.");
	}
	
	@Test
	public void testSetAttributeMethod() {
		String attribute = "Fire";
		monster.setAttribute(attribute);
		assertEquals(attribute, monster.getAttribute(), "Fire attribute expected.");
	}
	
	@Test
	public void testSetAtkMethod() {
		String atk = "?";
		monster.setAtk(atk);
		assertEquals(atk, monster.getAtk(), "? ATK points expected.");
	}
	
	@Test
	public void testSetDefMethod() {
		String def = "?";
		monster.setDef(def);
		assertEquals(def, monster.getDef(), "? DEF points expected.");
	}
	
	@Test
	public void testSetLevelMethod() {
		String level = "Link 4";
		monster.setLevel(level);
		assertEquals(level, monster.getLevel(), "Link 4 monster expected.");
	}
	
	@Test
	public void testGetTypeMethod() {
		assertEquals("Warrior", monster.getType(),"Warrior type expected.");
	}
	
	@Test
	public void testGetSummMethodMethod() {
		assertEquals("Fusion", monster.getSummMethod(),"Fusion summoning method expected.");
	}
	
	@Test
	public void testGetAttributeMethod() {
		assertEquals("Water", monster.getAttribute(),"Water attribute expected.");
	}
	
	@Test
	public void testGetAtkMethod() {
		assertEquals("2300", monster.getAtk(),"2300 ATK points expected.");
	}
	
	@Test
	public void testGetDefMethod() {
		assertEquals("XXXX", monster.getDef(),"XXXX DEF points expected.");
	}
	
	@Test
	public void testGetLevelMethod() {
		assertEquals("7", monster.getLevel(),"Level 7 expected.");
	}
}
