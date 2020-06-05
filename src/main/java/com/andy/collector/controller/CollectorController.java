package com.andy.collector.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.SpellCard;
import com.andy.collector.model.TrapCard;
import com.andy.collector.repository.CardRepository;

import io.swagger.v3.oas.annotations.Hidden;


@RestController
@RequestMapping("/collector")
public class CollectorController {
	
	@Autowired
	CardRepository cardControls;
	
	
	@PostMapping(value = "/add-monster", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MonsterCard> addCard(@RequestBody MonsterCard monsterCard) throws SQLException {
		boolean cardNew = cardControls.addCard(monsterCard);
		
		if(cardNew) {
			return new ResponseEntity<MonsterCard>(monsterCard, HttpStatus.OK);
		} else {
			return new ResponseEntity<MonsterCard>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PutMapping(value = "/edit-monster/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MonsterCard> updateCard(@PathVariable("id") String id, @RequestBody MonsterCard monsterCard) throws SQLException {

		boolean cardNew = cardControls.editCard(monsterCard, id);
		
		if(cardNew) {
			return new ResponseEntity<MonsterCard>(monsterCard, HttpStatus.OK);
		} else {
			return new ResponseEntity<MonsterCard>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Hidden
	@PostMapping(value = "/add-spell", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> addSpellCard(@RequestBody SpellCard spellCard) throws SQLException {
		boolean cardNew = cardControls.addCard(spellCard);
		
		if(cardNew) {
			return new ResponseEntity<Card>(spellCard, HttpStatus.OK);
		} else {
			return new ResponseEntity<Card>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(value = "/add-trap", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> addTrapCard(@RequestBody TrapCard trapCard) throws SQLException {
		boolean cardNew = cardControls.addCard(trapCard);
		
		if(cardNew) {
			return new ResponseEntity<Card>(trapCard, HttpStatus.OK);
		} else {
			return new ResponseEntity<Card>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PutMapping(value = "/edit-card/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> updateCard(@PathVariable("id") String id, @RequestBody Card card) throws SQLException {

		boolean cardNew = cardControls.editCard(card, id);
		
		if(cardNew) {
			return new ResponseEntity<Card>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/card-monster-details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MonsterCard> loadMonsterCardDetails(@PathVariable("id") String id) {
		MonsterCard monsterCard = null;
				
		try {
			monsterCard = cardControls.loadMonsterCardDetails(id);
			return new ResponseEntity<MonsterCard>(monsterCard, HttpStatus.OK);
			
		} catch (SQLException ex) {
			return new ResponseEntity<MonsterCard>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCard(@PathVariable("id") String id){
		try {
			cardControls.deleteCard(id);
			return new ResponseEntity<String>("Card deleted.",HttpStatus.OK);
		} catch (SQLException ex) {
			return new ResponseEntity<String>("Card not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/add-note/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCardNote(@RequestBody String note, @PathVariable("id") String id) throws SQLException {
		boolean notes = cardControls.addNotes(note, id);
		
		if(notes) {
			return new ResponseEntity<String>("note saved", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/load-note/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loadCardNote(@PathVariable("id") String id) throws SQLException {
		String notes = cardControls.loadNotes(id);
		
		if(notes != null) {
			return new ResponseEntity<String>(notes,HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/show-all-cards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Card>> loadAllCards() throws SQLException {
		ArrayList<Card> cards = cardControls.showAllCards();
		
		if(cards != null) {
			return new ResponseEntity<ArrayList<Card>> (cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<ArrayList<Card>> (HttpStatus.NOT_FOUND);
		}
		
	}
}
