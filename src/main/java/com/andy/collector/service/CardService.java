package com.andy.collector.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.Note;
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
		
		Note oldNote = card.getNote();
		oldNote.setIdNote(id+1);
		card.setNote(oldNote);
		
		cardRepository.save(card);
	}
	
	//edit spell or trap card details by id
	public void editCard(Card card, int id) {
		card.setId(id);
		
		Note oldNote = card.getNote();
		oldNote.setIdNote(id+1);
		card.setNote(oldNote);
		
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
}
