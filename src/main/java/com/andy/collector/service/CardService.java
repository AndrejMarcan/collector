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
import com.andy.collector.repository.CardRepository;
import com.andy.collector.repository.model.Card;
import com.andy.collector.repository.model.MonsterCard;
import com.andy.collector.repository.model.SpellCard;
import com.andy.collector.repository.model.TrapCard;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;

@Service
public class CardService {
	private MapperFacade mapper;
	
	@Autowired
	private CardRepository cardRepository;
	
	CardService(@Autowired MapperService mapperService) {
		this.mapper = mapperService.getFacade();
	}
	
	
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
		List<CardDTO> cardsDTO = cards.stream().map(p -> mapForFindAll(p)).collect(Collectors.toList());
		
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
			CardDTO cardDTO = mapForFindAll(card);
			return cardDTO;
		} else {
			return null;
		}
	}
	
	//add new spell card to DB
	private void addNewSpellCard(CardDTO cardDTO) {
		Card card = mapper.map((SpellCardDTO) cardDTO, SpellCard.class);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {
		Card card = mapper.map((TrapCardDTO) cardDTO, TrapCard.class);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {
		Card card = mapper.map((MonsterCardDTO) cardDTO, MonsterCard.class);
		
		cardRepository.save(card);
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {
	    MonsterCard monsterCard = mapper.map((MonsterCardDTO) cardDTO, MonsterCard.class);
	    monsterCard.setId(id);
	    
	    cardRepository.save(monsterCard);
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
	    SpellCard spellCard = mapper.map((SpellCardDTO) cardDTO, SpellCard.class);
	    spellCard.setId(id);
	    
	    cardRepository.save(spellCard);
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
	    TrapCard trapCard = mapper.map((TrapCardDTO) cardDTO, TrapCard.class);
	    trapCard.setId(id);
	    
	    cardRepository.save(trapCard);
	}
	
	private CardDTO mapForFindAll(Card card) {
		CardDTO cardDTO =null;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = mapper.map((SpellCard) card, SpellCardDTO.class);
	        	break;
	        case "Trap Card":
	        	cardDTO = mapper.map((TrapCard) card, TrapCardDTO.class);
	        	break;
	        case "Monster Card":
	        	cardDTO = mapper.map((MonsterCard) card, MonsterCardDTO.class);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
	}
}
