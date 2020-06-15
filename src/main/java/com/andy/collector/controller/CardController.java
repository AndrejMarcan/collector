package com.andy.collector.controller;

import java.sql.SQLException;
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
	public void addCard(@RequestBody SpellCard spellCard) throws SQLException {
		cardService.saveCard(spellCard);
		
	}
	
	@PostMapping(value = "/trap-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addCard(@RequestBody TrapCard trapCard) throws SQLException {
		cardService.saveCard(trapCard);
		
	}
	
	@PostMapping(value = "/monster-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addCard(@RequestBody MonsterCard monsterCard) throws SQLException {
		cardService.saveCard(monsterCard);
	}
	
	@PutMapping(value = "/edit-monster/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateMonsterCard(@PathVariable("id") String id, @RequestBody MonsterCard card) throws SQLException {

		cardService.editMonsterCard(card, Integer.valueOf(id));
	}
	
	@PutMapping(value = "/edit-trap-card/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateCard(@PathVariable("id") String id, @RequestBody TrapCard card) throws SQLException {

		cardService.editCard(card, Integer.valueOf(id));
	}
	
	@PutMapping(value = "/edit-spell-card/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateCard(@PathVariable("id") String id, @RequestBody SpellCard card) throws SQLException {

		cardService.editCard(card, Integer.valueOf(id));
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCard(@PathVariable("id") String id){
		cardService.deleteCardById(Integer.valueOf(id));
	}
	
	@Hidden
	@DeleteMapping(value = "/delete/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAllCard(){
		cardService.deleteAll();
	}
	
	@GetMapping(value = "/show-all-cards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Card>> loadAllCards() throws SQLException {
		List<Card> cards = cardService.getAllCards();
		
		if(cards != null) {
			return new ResponseEntity<List<Card>> (cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Card>> (HttpStatus.NOT_FOUND);
		}
		
	}
}
