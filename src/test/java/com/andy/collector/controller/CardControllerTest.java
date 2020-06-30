package com.andy.collector.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;
import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.Note;
import com.andy.collector.model.SpellCard;
import com.andy.collector.model.TrapCard;
import com.andy.collector.service.CardService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CardControllerTest {
	protected CardController controller;
	protected Note note;
	protected SpellCard spell;
	protected TrapCard trap;
	protected MonsterCard monster;
	
	@Autowired 
	CardService cardService;
	
	@BeforeAll
	void init() {
		controller = new CardController(cardService);
		
		note = new Note();		
		note.setNote("testNote");
		
		spell = new SpellCard();
		spell.setId(3000);
		spell.setName("TestSpell");
		spell.setEdition(Editions.UE);
		spell.setRarity(Rarities.COM);
		spell.setSet("DDS");
		spell.setNote(note);
		spell.setLanguage("English");
		spell.setType("Field Spell Card");
		
		trap = new TrapCard();
		trap.setId(3100);
		trap.setName("TestTrap");
		trap.setEdition(Editions.FE);
		trap.setRarity(Rarities.RARE);
		trap.setSet("DDS");
		trap.setNote(note);
		trap.setLanguage("English");
		trap.setType("Counter Trap Card");
		
		monster = new MonsterCard();
		monster.setId(3200);
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
	@Order(1)
	public void testAddSpellCard() throws SQLException {
		ResponseEntity<String> ent = controller.addCard(spell);
		assertEquals(new ResponseEntity<String>("spell saved",HttpStatus.OK), ent, "HttpStatus should be 200 OK");	
		assertFalse(ent.equals(new ResponseEntity<String>(HttpStatus.CONFLICT)));
	}
	
	@Test
	@Order(2)
	public void testAddTrapCard() throws SQLException {
		ResponseEntity<String> ent = controller.addCard(trap);
		assertEquals(new ResponseEntity<String>("trap saved",HttpStatus.OK), ent, "HttpStatus should be 200 OK");		
	}
	
	@Test
	@Order(3)
	public void testAddMonsterCard() throws SQLException {
		ResponseEntity<String> ent = controller.addCard(monster);
		assertEquals(new ResponseEntity<String>("monster saved",HttpStatus.OK), ent, "HttpStatus should be 200 OK");		
	}
	
	@Test
	@Order(4)
	public void testUpdateSpellCard() throws SQLException {
		ResponseEntity<String> ent = controller.updateCard("53", spell);
		assertEquals(new ResponseEntity<String>("spell edited",HttpStatus.OK), ent);		
	}
	
	@Test
	@Order(5)
	public void testUpdateTrapCard() throws SQLException {
		ResponseEntity<String> ent = controller.updateCard("54", trap);
		assertEquals(new ResponseEntity<String>("trap edited",HttpStatus.OK), ent);		
	}
	
	@Test
	@Order(6)
	public void testUpdateMonsterCard() throws SQLException {
		ResponseEntity<String> ent = controller.updateMonsterCard("55", monster);
		assertEquals(new ResponseEntity<String>("monster edited",HttpStatus.OK), ent);			
	}
	
	@Test
	@Order(7)
	public void testShowCardById() throws SQLException {
		ResponseEntity<Card> ent = controller.showCardById("55");
		assertEquals(new ResponseEntity<Card> (ent.getBody(),HttpStatus.OK), ent);			
	}
	
	@Test
	@Order(8)
	public void testShowAllCards() throws SQLException {
		ResponseEntity<List<Card>> ent = controller.loadAllCards();
		assertEquals(new ResponseEntity<List<Card>>(ent.getBody(), HttpStatus.OK), ent);			
	}
	
	
	@Disabled
	@Test
	@Order(9)
	public void testDeleteAll() throws SQLException {
		ResponseEntity<String> ent1 = controller.deleteAllCard();		
		assertEquals(new ResponseEntity<String>("ALL CARDS WERE DELETED",HttpStatus.OK), ent1);
	}
	
	@ParameterizedTest
	@Order(10)
	@ValueSource(strings = {"102", "103", "104"})
	public void testDeleteCardNOT(String string) throws SQLException {
		ResponseEntity<String> ent1 = controller.deleteCard(string);
		
		assertEquals(new ResponseEntity<String>("card not found",HttpStatus.NOT_FOUND),ent1);
	}
	
	@ParameterizedTest
	@Order(10)
	@ValueSource(strings = {"1102", "1103", "1104"})
	public void testDeleteCard(String string) throws SQLException {
		ResponseEntity<String> ent1 = controller.deleteCard(string);
		
		assertEquals(new ResponseEntity<String>("delete succesful",HttpStatus.OK),ent1);
	}
	
	
}
