package com.andy.collector.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.dto.UserDTO;
import com.andy.collector.enums.Editions;
import com.andy.collector.enums.Rarities;
import com.andy.collector.repository.CardRepository;
import com.andy.collector.repository.model.Card;
import com.andy.collector.repository.model.MonsterCard;
import com.andy.collector.repository.model.SpellCard;
import com.andy.collector.repository.model.TrapCard;
import com.andy.collector.repository.model.User;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private MapperService mapper;
	
	//add new spell card to DB
	public void addNewCard(CardDTO cardDTO) {
		if(cardDTO.getCardType().equalsIgnoreCase("Monster Card")) {
			addNewMonsterCard(cardDTO);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Spell Card")) {
			addNewSpellCard(cardDTO);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Trap Card")) {
			addNewTrapCard(cardDTO);
		}
	}

	//edit monster card details by ID
	public void editCard(CardDTO cardDTO, int id) {
		if(cardDTO.getCardType().equalsIgnoreCase("Monster Card")) {
			editMonsterCard(cardDTO, id);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Spell Card")) {
			editSpellCard(cardDTO, id);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Trap Card")) {
			editTrapCard(cardDTO, id);
		}
	}
	
	//delete card by id
	public void deleteCardById(int id) {
		cardRepository.deleteById(id);
	}
	
	//get list of all cards
	public List<CardDTO> getAllCards(){    
		List<Card> cards = cardRepository.findAll();
		List<CardDTO> cardsDTO = cards.stream().map(p -> mapper.mapForFindAll(p)).collect(Collectors.toList());
		
		return cardsDTO;
	}
	
	//delete all cards from db
	public void deleteAll() {
		cardRepository.deleteAll();
	}
	
	//get card by id
	public CardDTO findCardById(int id){
		
		Optional<Card> cardOpt = cardRepository.findById(id);
		
		if(cardOpt.isPresent()) {
			Card card = cardOpt.get();
			CardDTO cardDTO = mapper.mapForFindAll(card);
			return cardDTO;
		} else {
			return null;
		}
	}
	
	//add new spell card to DB
	private void addNewSpellCard(CardDTO cardDTO) {   
		Card card = mapper.getSpellCardBoundMapper().map((SpellCardDTO) cardDTO);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {
		Card card = mapper.getTrapCardBoundMapper().map((TrapCardDTO) cardDTO);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {
		Card card = mapper.getMonsterCardBoundMapper().map((MonsterCardDTO) cardDTO);
		
		cardRepository.save(card);
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {
	    MonsterCard monsterCard = mapper.getMonsterCardBoundMapper().map((MonsterCardDTO) cardDTO);
	    monsterCard.setId(id);
	    
	    cardRepository.save(monsterCard);
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
	    SpellCard spellCard = mapper.getSpellCardBoundMapper().map((SpellCardDTO) cardDTO);
	    spellCard.setId(id);
	    
	    cardRepository.save(spellCard);
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
	    TrapCard trapCard = mapper.getTrapCardBoundMapper().map((TrapCardDTO) cardDTO);
	    trapCard.setId(id);
	    
	    cardRepository.save(trapCard);
	}
}
