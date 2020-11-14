package com.andy.collector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.repository.postgres.CardRepositoryPostgres;
import com.andy.collector.repository.postgres.model.CardPostgres;
import com.andy.collector.repository.postgres.model.MonsterCardPostgres;
import com.andy.collector.repository.postgres.model.SpellCardPostgres;
import com.andy.collector.repository.postgres.model.TrapCardPostgres;

import ma.glasnost.orika.MapperFacade;

/**
 * Service class for card objects. 
 * 
 * @version		0.1 14. July 2020
 * @author 		Andrej Marcan
 */

@Service
@CacheConfig(cacheNames = "card_cache")
public class CardService {
	private static final Logger LOG = LoggerFactory.getLogger(CardService.class);
	
	private MapperFacade mapperPostgres;
	private CardRepositoryPostgres cardRepositoryPostgres;
	
	CardService(@Autowired MapperService mapperService, 
					@Autowired CardRepositoryPostgres cardRepositoryPostgres) {
		this.mapperPostgres = mapperService.getFacadePostgres();
		this.cardRepositoryPostgres = cardRepositoryPostgres;
	}
	
	
	//add new spell card to DB
	public void addNewCard(CardDTO cardDTO) {
		LOG.info("Trying to add new card to DB.");
		
		if(cardDTO.getCardType().equalsIgnoreCase("Monster Card")) {
			addNewMonsterCard(cardDTO);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Spell Card")) {
			addNewSpellCard(cardDTO);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Trap Card")) {
			addNewTrapCard(cardDTO);
		}
	}

	//edit monster card details by ID
	@CacheEvict(key = "#id")
	public void editCard(CardDTO cardDTO, int id) {
		LOG.info("Trying to edit card informations for id {} ", id);
		
		if(cardDTO.getCardType().equalsIgnoreCase("Monster Card")) {
			editMonsterCard(cardDTO, id);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Spell Card")) {
			editSpellCard(cardDTO, id);
		} else if(cardDTO.getCardType().equalsIgnoreCase("Trap Card")) {
			editTrapCard(cardDTO, id);
		}
	}
	
	//delete card by id
	@CacheEvict(key = "#id")
	public void deleteCardById(int id) {
		LOG.info("Trying to delete card with id {} ", id);
	    cardRepositoryPostgres.deleteById(id);
	}
	
	//get list of all cards
	public List<CardDTO> getAllCards(){
		LOG.info("Trying to get all cards from DB.");
		
		List<CardDTO> cardsDTO = new ArrayList<>();
	    List<CardPostgres> cards = cardRepositoryPostgres.findAll();
		cardsDTO = cards.stream().map(p -> mapForFindAllPostgres(p)).collect(Collectors.toList());
		return cardsDTO;
	}
	
	//delete all cards from db
	@CacheEvict(allEntries = true)
	public void deleteAll() {
		LOG.info("Tryint to delete all cards. ");	
	    cardRepositoryPostgres.deleteAll();
	}
	
	//get card by id
	@Cacheable(key = "#id", unless = "#result == null")
	public CardDTO findCardById(int id){
		LOG.info("Trying to get card information for id {} ", id);
		
		CardDTO cardDTO = null;
	    Optional<CardPostgres> cardOpt = cardRepositoryPostgres.findById(id);
			
		if(cardOpt.isPresent()) {		
			CardPostgres card = cardOpt.get();
			cardDTO = mapForFindAllPostgres(card);
		} else {
			return null;
		}
		return cardDTO;
	}
	
	//add new spell card to DB
	private void addNewSpellCard(CardDTO cardDTO) {
	    CardPostgres card = mapperPostgres.map((SpellCardDTO) cardDTO, SpellCardPostgres.class);    
		cardRepositoryPostgres.save(card);
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {   
	    CardPostgres card = mapperPostgres.map((TrapCardDTO) cardDTO, TrapCardPostgres.class);    
		cardRepositoryPostgres.save(card);
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {  
	    CardPostgres card = mapperPostgres.map((MonsterCardDTO) cardDTO, MonsterCardPostgres.class);    
		cardRepositoryPostgres.save(card);
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {  
	    MonsterCardPostgres monsterCard = mapperPostgres.map((MonsterCardDTO) cardDTO, MonsterCardPostgres.class);
		monsterCard.setId(id);		    
		cardRepositoryPostgres.save(monsterCard);
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
	    SpellCardPostgres spellCard = mapperPostgres.map((SpellCardDTO) cardDTO, SpellCardPostgres.class);
		spellCard.setId(id);		    
		cardRepositoryPostgres.save(spellCard);
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
	    TrapCardPostgres trapCard = mapperPostgres.map((TrapCardDTO) cardDTO, TrapCardPostgres.class);
		trapCard.setId(id);		    
		cardRepositoryPostgres.save(trapCard);
	}
	
	private CardDTO mapForFindAllPostgres(CardPostgres card) {
		CardDTO cardDTO =null;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = mapperPostgres.map((SpellCardPostgres) card, SpellCardDTO.class);
	        	break;
	        case "Trap Card":
	        	cardDTO = mapperPostgres.map((TrapCardPostgres) card, TrapCardDTO.class);
	        	break;
	        case "Monster Card":
	        	cardDTO = mapperPostgres.map((MonsterCardPostgres) card, MonsterCardDTO.class);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
	}
}
