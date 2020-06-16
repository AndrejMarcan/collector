package com.andy.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.model.Card;
import com.andy.collector.model.MonsterCard;
import com.andy.collector.model.Note;
import com.andy.collector.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	public void saveCard(Card card) {
		cardRepository.save(card);
	}
	
	public void editMonsterCard(MonsterCard card, int id) {
		card.setId(id);
		
		Note oldNote = card.getNote();
		oldNote.setIdNote(id+1);
		card.setNote(oldNote);
		
		cardRepository.save(card);
	}
	
	public void editCard(Card card, int id) {
		card.setId(id);
		
		Note oldNote = card.getNote();
		oldNote.setIdNote(id+1);
		card.setNote(oldNote);
		
		cardRepository.save(card);
	}
	
	public void deleteCardById(int id) {
		cardRepository.deleteById(id);
	}
	
	public List<Card> getAllCards(){
		return cardRepository.findAll();
	}
	
	public void deleteAll() {
		cardRepository.deleteAll();
	}
}
