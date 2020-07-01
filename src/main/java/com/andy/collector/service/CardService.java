package com.andy.collector.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.Card;
import com.andy.collector.dto.MonsterCard;
import com.andy.collector.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	//add new card to DB
	public void addNewCard(Card card) {
		cardRepository.save(card);
	}
	
	//edit monster card details by ID
	public void editMonsterCard(MonsterCard card, int id) {
		card.setId(id);
		cardRepository.save(card);
	}
	
	//edit spell or trap card details by id
	public void editCard(Card card, int id) {
		card.setId(id);		
		cardRepository.save(card);
	}
	
	//delete card by id
	public void deleteCardById(int id) {
		cardRepository.deleteById(id);
	}
	
	//get list of all cards
	public List<Card> getAllCards(){
		return cardRepository.findAll();
	}
	
	//delete all cards from db
	public void deleteAll() {
		cardRepository.deleteAll();
	}
	
	//get card by id
	public Optional<Card> findCardById(int id){
		return cardRepository.findById(id);
	}
	
	//get all cards with name
	public List<Card> getAllCardsWithName(String name){
		List<Card> allCards = cardRepository.findAll();
		List<Card> cards = allCards.stream().filter(p -> p.getName().equals(name))
                .collect(Collectors.toList());
		
		return cards;
	}
	
}
