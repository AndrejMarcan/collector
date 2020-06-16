package com.andy.collector.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;
import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.Note;
import com.andy.collector.model.SpellCard;
import com.andy.collector.model.TrapCard;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CardServiceTest {
	
	protected static SpellCard spell;
	protected static TrapCard trap;
	protected static MonsterCard monster;
	protected static Note note;
	
	@Autowired
	protected CardService cs;
	
	@BeforeAll
	public static void init() {
		note = new Note();		
		note.setNote("testNote");
		
		spell = new SpellCard();
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		
		trap = new TrapCard();
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		
		monster = new MonsterCard();
		monster.setName("TestMonster");
		monster.setEdition(Editions.LE);
		monster.setRarity(Rarities.GHOST);
		monster.setSet("DDS");
		monster.setNote(note);
		monster.setLanguage("English");
		monster.setType("Warrior");
		monster.setSummMethod("Fusion");
		monster.setAttribute("Water");
		monster.setAtk("2300");
		monster.setDef("XXXX");
		monster.setLevel("7");
		
		SpringApplication app = new SpringApplication(Main.class);
     	app.run();
	}
	
	@Test
	public void testAddNewCardMethod() {
		int sizeBefore = cs.getAllCards().size();
		cs.addNewCard(monster);
		int sizeAfter = cs.getAllCards().size();
		
		assertTrue(sizeBefore < sizeAfter);
		
		Card cardFromDB = cs.getAllCards().get(sizeAfter-1);
		
		assertEquals(monster.getName(), cardFromDB.getName(),"test name and name in DB doesnt match");
		assertEquals(monster.getEdition(), cardFromDB.getEdition(),"test edition and edition in DB doesnt match");
		assertEquals(monster.getRarity(), cardFromDB.getRarity(),"test rarity and rarity in DB doesnt match");
		assertEquals(monster.getSet(), cardFromDB.getSet(),"test set and set in DB doesnt match");
		assertEquals(monster.getType(), cardFromDB.getType(),"test type and type in DB doesnt match");
		assertEquals(monster.getLanguage(), cardFromDB.getLanguage(),"test language in DB doesnt match");
		assertEquals(monster.getNote().getNote(), cardFromDB.getNote().getNote(),"test note text and note text in DB doesnt match");
	}
	
	@Test
	public void testFindCardByIdMethod() {
		Optional<Card> cardOpt = cs.findCardById(5);		
		assertTrue(cardOpt.isPresent());		
		Card card = cardOpt.get();		
		assertEquals(monster.getName(), card.getName(),"test name and name in DB doesnt match");
	}
	
	@Disabled // tested 
	@Test
	public void testEditMonsterCardMethod() {
		cs.editMonsterCard(monster, 5);
		Optional<Card> cardOpt = cs.findCardById(5);
		assertTrue(cardOpt.isPresent());
		
		Card card = cardOpt.get();
		assertEquals(monster.getName(), card.getName(),"test name and name in DB doesnt match");
		
	}
	
	@Disabled //while testing delete methods this test would need to be updated all the time
	@Test
	public void testGetallCardsMethod() {
		List<Card> list = cs.getAllCards();
		
		assertTrue("List size should be 4",4 == list.size());
		
		assertEquals("JDBC spell", list.get(0).getName(),"Card name at this position should be JDBC spell");
		assertEquals("TestMonster", list.get(1).getName(),"Card name at this position should be TestMonster");
		assertEquals("newTrap", list.get(2).getName(),"Card name at this position should be newTrap");
		assertEquals("TestMonster", list.get(3).getName(),"Card name at this position should be TestMonster");
	}
	
	@Disabled //card with id 7 no longer exists in DB
	@Test
	public void testDeleteCardByIdMethod() {
		Optional<Card> cardOpt = cs.findCardById(7);		
		assertTrue("card should be found", cardOpt.isPresent());		
		
		cs.deleteCardById(7);
		
		Optional<Card> cardOpt2 = cs.findCardById(7);		
		assertFalse("card should NOT be found", cardOpt2.isPresent());		
	}
	
}
