package com.andy.collector.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CardServiceTest {
	
	protected static SpellCardDTO spell;
	protected static TrapCardDTO trap;
	protected static MonsterCardDTO monster;
	protected static NoteDTO note;
	
	@Autowired
	protected CardService cs;
	
	@BeforeAll
	public void init() {
		note = new NoteDTO();		
		note.setNote("testNote");
		
		spell = new SpellCardDTO();
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		//spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		
		trap = new TrapCardDTO();
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		//trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		
		monster = new MonsterCardDTO();
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
		
		//SpringApplication app = new SpringApplication(Main.class);
     	//app.run();
	}
	
	@Test
	@Order(1)
	public void testAddNewCardMethod() {
		int sizeBefore = cs.getAllCards().size();
		cs.addNewCard(monster);
		int sizeAfter = cs.getAllCards().size();		
		assertTrue(sizeBefore < sizeAfter);
	}
	
	
	@Test
	@Order(2)
	public void testFindCardByIdMethod() {
		Optional<CardDTO> cardOpt = cs.findCardById(53);		
		assertTrue(cardOpt.isPresent());		
		CardDTO card = cardOpt.get();		
		assertEquals("TestSpell", card.getName(),"test name and name in DB doesnt match");
	}
	
	@Test
	@Order(3)
	public void testEditMonsterCardMethod() {
		cs.editMonsterCard(monster, 55);
		Optional<CardDTO> cardOpt = cs.findCardById(55);
		assertTrue(cardOpt.isPresent());
		
		CardDTO card = cardOpt.get();
		assertEquals("TestMonster", card.getName(),"test name and name in DB doesnt match");
		
	}
	
	@Test
	public void testEditCardMethod() {
		cs.editCard(spell, 53);
		Optional<CardDTO> cardOpt = cs.findCardById(53);
		assertTrue(cardOpt.isPresent());
		
		CardDTO card = cardOpt.get();
		assertEquals("TestSpell", card.getName(),"test name and name in DB doesnt match");
		
	}
	
	@Test
	@Order(4)
	public void testGetallCardsMethod() {
		List<CardDTO> list = cs.getAllCards();
		assertTrue(list.size()>0);
	}
	
	@Test
	@Order(5)
	public void testDeleteCardByIdMethod() {
		Optional<CardDTO> cardOpt = cs.findCardById(752);		
		assertTrue("card should be found", cardOpt.isPresent());		
		
		cs.deleteCardById(752);
		
		Optional<CardDTO> cardOpt2 = cs.findCardById(752);		
		assertFalse("card should NOT be found", cardOpt2.isPresent());		
	}
	
	@Disabled
	@Test
	@Order(6)
	public void testDeleteAllCardsMethod() {
		cs.deleteAll();
		assertTrue(cs.getAllCards().size()==0);
	}
	
	@AfterAll
	public void initAfter() {
		note = new NoteDTO();		
		note.setNote("testNote");
		
		spell = new SpellCardDTO();
		spell.setId(1);
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		//spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		
		trap = new TrapCardDTO();
		trap.setId(2);
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		//trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		
		monster = new MonsterCardDTO();
		monster.setId(3);
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
		
		cs.addNewCard(spell);
		cs.addNewCard(trap);
		cs.addNewCard(monster);
	}
}
