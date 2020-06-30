package com.andy.collector.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	//add new card to DB
	public void addNewCard(CardDTO card) {
		cardRepository.save(card);
	}
	
	//edit monster card details by ID
	public void editMonsterCard(MonsterCardDTO card, int id) {
		card.setId(id);
		cardRepository.save(card);
	}
	
	//edit spell or trap card details by id
	public void editCard(CardDTO card, int id) {
		card.setId(id);		
		cardRepository.save(card);
	}
	
	//delete card by id
	public void deleteCardById(int id) {
		cardRepository.deleteById(id);
	}
	
	//get list of all cards
	public List<CardDTO> getAllCards(){
		return cardRepository.findAll();
	}
	
	//delete all cards from db
	public void deleteAll() {
		cardRepository.deleteAll();
	}
	
	//get card by id
	public Optional<CardDTO> findCardById(int id){
		return cardRepository.findById(id);
	}
}
