package com.andy.collector.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;

@TestMethodOrder(OrderAnnotation.class)
@Testcontainers
@Profile("test")
@SpringBootTest
@ContextConfiguration(initializers = CardServicePostgresTest.Initializer.class)
public class CardServicePostgresTest {
	protected static SpellCardDTO spell;
	protected static TrapCardDTO trap;
	protected static MonsterCardDTO monster;
	protected static NoteDTO note;
	
	@Autowired
	CardService cardService;
	
	@Container
	public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer()
          																	.withDatabaseName("sampledb")
          																	.withUsername("sampleuser")
          																	.withPassword("samplepwd");
	
	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
	    public void initialize(ConfigurableApplicationContext context) {
	        TestPropertyValues.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl())
	                	.applyTo(context.getEnvironment());
	        TestPropertyValues.of("spring.datasource.username=" + postgreSQLContainer.getUsername())
	        			.applyTo(context.getEnvironment());
	        TestPropertyValues.of("spring.datasource.password=" + postgreSQLContainer.getPassword())
	       				.applyTo(context.getEnvironment());
    }}
	
	@BeforeAll
	public static void init() {
		note = new NoteDTO();
		note.setNote("hi");
		note.setIdCard(1);
		Collection list = new ArrayList<NoteDTO>();
		list.add(note);
		
		spell = new SpellCardDTO();
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		spell.setNotes(list);
		
		trap = new TrapCardDTO();
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		trap.setNotes(list);
		
		monster = new MonsterCardDTO();
		monster.setName("TestMonster");
		monster.setEdition(Editions.LE);
		monster.setRarity(Rarities.GHOST);
		monster.setSet("DDS");
		monster.setLanguage("English");
		monster.setType("Warrior");
		monster.setSummMethod("Fusion");
		monster.setAttribute("Water");
		monster.setAtk("2300");
		monster.setDef("XXXX");
		monster.setLevel("7");
		monster.setNotes(list);
	}
	
	@Test
	@Order(1)
	public void addNewCardTest() {		
		cardService.addNewCard(spell);
		cardService.addNewCard(trap);
		cardService.addNewCard(monster);
		
		int size = cardService.getAllCards().size();
		
		assertTrue(size==3);
	}
	
	@Test
	@Order(2)
	public void findCardByIdTest() {
		CardDTO card = cardService.findCardById(1);
		assertEquals(spell.getName(), card.getName(), "Name should be TestSpell");
		assertEquals(spell.getEdition(), card.getEdition(), "UE expected");
		assertEquals(spell.getRarity(), card.getRarity(), "COM expected");
		assertEquals(spell.getSet(), card.getSet());
		assertEquals(spell.getLanguage(), card.getLanguage());
		
		card = cardService.findCardById(2);
		assertEquals(trap.getName(), card.getName(), "Name should be TestTrap");
		assertEquals(trap.getEdition(), card.getEdition(), "FE expected");
		assertEquals(trap.getRarity(), card.getRarity(), "RARE expected");
		assertEquals(trap.getSet(), card.getSet());
		assertEquals(trap.getLanguage(), card.getLanguage());
		
		card = cardService.findCardById(3);
		assertEquals(monster.getName(), card.getName(), "Name should be TestMonster");
		assertEquals(monster.getEdition(), card.getEdition(), "/lE expected");
		assertEquals(monster.getRarity(), card.getRarity(), "GHOST expected");
		assertEquals(monster.getSet(), card.getSet());
		assertEquals(monster.getLanguage(), card.getLanguage());		
	}
	
	@Test
	@Order(3)
	public void editCardByIdTest() {
		spell.setName("edited spell");
		trap.setLanguage("spanish");
		monster.setSet("LOB");
		
		cardService.editCard(spell, 1);
		cardService.editCard(trap, 2);
		cardService.editCard(monster, 3);
		
		CardDTO card = cardService.findCardById(1);
		assertEquals(spell.getName(), card.getName(), "Name should be TestSpell");
		
		card = cardService.findCardById(2);
		assertEquals(trap.getLanguage(), card.getLanguage());
		
		card = cardService.findCardById(3);
		assertEquals(monster.getSet(), card.getSet());
	}
	
	@Test
	@Order(4)
	public void deleteCardByIdTest() {
		spell.setId(1);
		cardService.deleteCardById(1);
		List<CardDTO> list = cardService.getAllCards();
		assertFalse(list.contains(spell));
		assertTrue(list.size()==2);
	}
	
	@Test
	@Order(5)
	public void deleteAllCardsTest() {
		cardService.deleteAll();
		List<CardDTO> list = cardService.getAllCards();
		assertTrue(list.size()==0);
	}
}	
