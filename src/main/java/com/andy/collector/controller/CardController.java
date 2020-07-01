package com.andy.collector.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

import com.andy.collector.dto.Card;
import com.andy.collector.dto.MonsterCard;
import com.andy.collector.dto.SpellCard;
import com.andy.collector.dto.TrapCard;
import com.andy.collector.service.CardService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/collector")
public class CardController {
		private final CardService cardService;
		
	CardController( @Autowired CardService cardService){
		this.cardService = cardService;
	}
	
	@PostMapping(value = "/spell-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCard(@RequestBody SpellCard spellCard) throws SQLException {
		
		try {
			cardService.addNewCard(spellCard);
			return new ResponseEntity<String>("spell saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping(value = "/trap-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCard(@RequestBody TrapCard trapCard) throws SQLException {
		
		try {
			cardService.addNewCard(trapCard);
			return new ResponseEntity<String>("trap saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(value = "/monster-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCard(@RequestBody MonsterCard monsterCard) throws SQLException {
		
		try {
			cardService.addNewCard(monsterCard);
			return new ResponseEntity<String>("monster saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value = "/edit-monster/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateMonsterCard(@PathVariable("id") String id, @RequestBody MonsterCard card) throws SQLException {

		try {
			cardService.editMonsterCard(card, Integer.valueOf(id));
			return new ResponseEntity<String>("monster edited",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("card not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/edit-trap-card/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCard(@PathVariable("id") String id, @RequestBody TrapCard card) throws SQLException {

		try {
			cardService.editCard(card, Integer.valueOf(id));
			return new ResponseEntity<String>("trap edited",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("card not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/edit-spell-card/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCard(@PathVariable("id") String id, @RequestBody SpellCard card) throws SQLException {

		try {
			cardService.editCard(card, Integer.valueOf(id));
			return new ResponseEntity<String>("spell edited",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("card not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCard(@PathVariable("id") String id){
		
		try {
			cardService.deleteCardById(Integer.valueOf(id));
			return new ResponseEntity<String>("delete succesful",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("card not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@Hidden
	@DeleteMapping(value = "/delete/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAllCard(){
		try {
			cardService.deleteAll();
			return new ResponseEntity<String>("ALL CARDS WERE DELETED",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/show-all-cards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Card>> showAllCards() throws SQLException {
		List<Card> cards = cardService.getAllCards();
		
		if(cards != null) {
			return new ResponseEntity<List<Card>> (cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Card>> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/show-all-cards/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Card>> showAllCardsWithName(@PathVariable("name") String name) throws SQLException {
		List<Card> cards = cardService.getAllCardsWithName(name);		
		if(cards != null) {
			return new ResponseEntity<List<Card>> (cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Card>> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/show-card/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> showCardById(@PathVariable("id") String id) throws SQLException {
		Optional<Card> cardOpt = cardService.findCardById(Integer.valueOf(id));
		
		if(cardOpt.isPresent()) {
			return new ResponseEntity<Card> (cardOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Card> (HttpStatus.NOT_FOUND);
		}		
	}
	
	
}
