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
import com.andy.collector.dto.Card;
import com.andy.collector.dto.MonsterCard;
import com.andy.collector.dto.Note;
import com.andy.collector.dto.SpellCard;
import com.andy.collector.dto.TrapCard;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CardServiceTest {
	
	protected static SpellCard spell;
	protected static TrapCard trap;
	protected static MonsterCard monster;
	protected static Note note;
	
	@Autowired
	protected CardService cs;
	
	@BeforeAll
	public void init() {
		note = new Note();		
		note.setNote("testNote");
		
		spell = new SpellCard();
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		//spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		
		trap = new TrapCard();
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		//trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		
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
		Optional<Card> cardOpt = cs.findCardById(53);		
		assertTrue(cardOpt.isPresent());		
		Card card = cardOpt.get();		
		assertEquals("TestSpell", card.getName(),"test name and name in DB doesnt match");
	}
	
	@Test
	@Order(3)
	public void testEditMonsterCardMethod() {
		cs.editMonsterCard(monster, 55);
		Optional<Card> cardOpt = cs.findCardById(55);
		assertTrue(cardOpt.isPresent());
		
		Card card = cardOpt.get();
		assertEquals("TestMonster", card.getName(),"test name and name in DB doesnt match");
		
	}
	
	@Test
	public void testEditCardMethod() {
		cs.editCard(spell, 53);
		Optional<Card> cardOpt = cs.findCardById(53);
		assertTrue(cardOpt.isPresent());
		
		Card card = cardOpt.get();
		assertEquals("TestSpell", card.getName(),"test name and name in DB doesnt match");
		
	}
	
	@Test
	@Order(4)
	public void testGetallCardsMethod() {
		List<Card> list = cs.getAllCards();
		assertTrue(list.size()>0);
	}
	
	@Test
	@Order(5)
	public void testDeleteCardByIdMethod() {
		Optional<Card> cardOpt = cs.findCardById(752);		
		assertTrue("card should be found", cardOpt.isPresent());		
		
		cs.deleteCardById(752);
		
		Optional<Card> cardOpt2 = cs.findCardById(752);		
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
		note = new Note();		
		note.setNote("testNote");
		
		spell = new SpellCard();
		spell.setId(1);
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		//spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		
		trap = new TrapCard();
		trap.setId(2);
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		//trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		
		monster = new MonsterCard();
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
