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
	
	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	
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
		mapperFactory.classMap(Card.class, CardDTO.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    
		List<Card> cards = cardRepository.findAll();
		List<CardDTO> cardsDTO = cards.stream().map(p -> mapper.map(p, CardDTO.class)).collect(Collectors.toList());
		
		return cardsDTO;
	}
	
	//delete all cards from db
	public void deleteAll() {
		cardRepository.deleteAll();
	}
	
	//get card by id
	public CardDTO findCardById(int id){
		mapperFactory.classMap(Card.class, SpellCardDTO.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    
		Optional<Card> cardOpt = cardRepository.findById(id);
		
		if(cardOpt.isPresent()) {
			Card card = cardOpt.get();
			CardDTO cardDTO = mapper.map(card, SpellCardDTO.class);
			return cardDTO;
		} else {
			return null;
		}
	}
	
	//add new spell card to DB
	private void addNewSpellCard(CardDTO cardDTO) {
		mapperFactory.classMap(CardDTO.class, SpellCard.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		    
		Card card = mapper.map(cardDTO, SpellCard.class);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {
		mapperFactory.classMap(CardDTO.class, TrapCard.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		    
		Card card = mapper.map(cardDTO, TrapCard.class);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {
		mapperFactory.classMap(CardDTO.class, MonsterCard.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		    
		Card card = mapper.map(cardDTO, MonsterCard.class);
		
		cardRepository.save(card);
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {
		mapperFactory.classMap(CardDTO.class, MonsterCard.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    MonsterCard monsterCard = mapper.map(cardDTO, MonsterCard.class);
	    monsterCard.setId(id);
	    
	    cardRepository.save(monsterCard);
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
		mapperFactory.classMap(CardDTO.class, SpellCard.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    SpellCard spellCard = mapper.map(cardDTO, SpellCard.class);
	    spellCard.setId(id);
	    
	    cardRepository.save(spellCard);
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
		mapperFactory.classMap(CardDTO.class, TrapCard.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    TrapCard trapCard = mapper.map(cardDTO, TrapCard.class);
	    trapCard.setId(id);
	    
	    cardRepository.save(trapCard);
	}
}
