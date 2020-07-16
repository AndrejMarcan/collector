package com.andy.collector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.repository.mongo.CardRepositoryMongo;
import com.andy.collector.repository.mongo.model.CardMongo;
import com.andy.collector.repository.mongo.model.MonsterCardMongo;
import com.andy.collector.repository.mongo.model.SpellCardMongo;
import com.andy.collector.repository.mongo.model.TrapCardMongo;
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
public class CardService {
	
	@Value("${andy.database.picker}")
	private String layer; //try something else...
	
	
	private MapperFacade mapperMongo;
	private MapperFacade mapperPostgres;
	private CardRepositoryMongo cardRepositoryMongo;
	private CardRepositoryPostgres cardRepositoryPostgres;
	
	CardService(@Autowired MapperService mapperService, @Autowired CardRepositoryMongo cardRepositoryMongo, 
					@Autowired CardRepositoryPostgres cardRepositoryPostgres) {
		this.mapperMongo = mapperService.getFacadeMongo();
		this.mapperPostgres = mapperService.getFacadePostgres();
		this.cardRepositoryMongo = cardRepositoryMongo;
		this.cardRepositoryPostgres = cardRepositoryPostgres;
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
		if (layer.equals("mongo")) {		
			cardRepositoryMongo.deleteById(id);
	    } else if (layer.equals("postgres")) {	   
	    	cardRepositoryPostgres.deleteById(id);
	    }
	}
	
	//get list of all cards
	public List<CardDTO> getAllCards(){  
		List<CardDTO> cardsDTO = new ArrayList<>();
		if (layer.equals("mongo")) {		
			List<CardMongo> cards = cardRepositoryMongo.findAll();
			cardsDTO = cards.stream().map(p -> mapForFindAll(p)).collect(Collectors.toList());
	    } else if (layer.equals("postgres")) {	   
	    	List<CardPostgres> cards = cardRepositoryPostgres.findAll();
			cardsDTO = cards.stream().map(p -> mapForFindAllPostgres(p)).collect(Collectors.toList());
	    }
		return cardsDTO;
	}
	
	//delete all cards from db
	public void deleteAll() {
		if (layer.equals("mongo")) {			
			cardRepositoryMongo.deleteAll();
			
	    } else if (layer.equals("postgres")) {	    	
	    	cardRepositoryPostgres.deleteAll();
	    }
	}
	
	//get card by id
	public CardDTO findCardById(int id){
		CardDTO cardDTO = null;
		if (layer.equals("mongo")) {		
			Optional<CardMongo> cardOpt = cardRepositoryMongo.findById(id);
			
			if(cardOpt.isPresent()) {		
				CardMongo card = cardOpt.get();
				cardDTO = mapForFindAll(card);
			} else {
				return null;
			}
	    } else if (layer.equals("postgres")) {	   
	    	Optional<CardPostgres> cardOpt = cardRepositoryPostgres.findById(id);
			
			if(cardOpt.isPresent()) {		
				CardPostgres card = cardOpt.get();
				cardDTO = mapForFindAllPostgres(card);
			} else {
				return null;
			}
	    }
		return cardDTO;
	}
	
	//add new spell card to DB
	private void addNewSpellCard(CardDTO cardDTO) {
		if (layer.equals("mongo")) {		
			CardMongo card = mapperMongo.map((SpellCardDTO) cardDTO, SpellCardMongo.class);    
			cardRepositoryMongo.save(card);
	    } else if (layer.equals("postgres")) {	   
	    	CardPostgres card = mapperPostgres.map((SpellCardDTO) cardDTO, SpellCardPostgres.class);    
			cardRepositoryPostgres.save(card);
	    }
		
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {
		if (layer.equals("mongo")) {		
			CardMongo card = mapperMongo.map((TrapCardDTO) cardDTO, TrapCardMongo.class);    
			cardRepositoryMongo.save(card);
	    } else if (layer.equals("postgres")) {	   
	    	CardPostgres card = mapperPostgres.map((TrapCardDTO) cardDTO, TrapCardPostgres.class);    
			cardRepositoryPostgres.save(card);
	    }	
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {
		if (layer.equals("mongo")) {		
			CardMongo card = mapperMongo.map((MonsterCardDTO) cardDTO, MonsterCardMongo.class);
			cardRepositoryMongo.save(card);
	    } else if (layer.equals("postgres")) {	   
	    	CardPostgres card = mapperPostgres.map((MonsterCardDTO) cardDTO, MonsterCardPostgres.class);    
			cardRepositoryPostgres.save(card);
	    }
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {
		if (layer.equals("mongo")) {		
			MonsterCardMongo monsterCard = mapperMongo.map((MonsterCardDTO) cardDTO, MonsterCardMongo.class);
		    monsterCard.setId(id);		    
		    cardRepositoryMongo.save(monsterCard);
		    
	    } else if (layer.equals("postgres")) {	   
	    	MonsterCardPostgres monsterCard = mapperPostgres.map((MonsterCardDTO) cardDTO, MonsterCardPostgres.class);
		    monsterCard.setId(id);		    
		    cardRepositoryPostgres.save(monsterCard);
	    }
	    
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
		if (layer.equals("mongo")) {		
			 SpellCardMongo spellCard = mapperMongo.map((SpellCardDTO) cardDTO, SpellCardMongo.class);
			 spellCard.setId(id);
			 cardRepositoryMongo.save(spellCard);
			 
	    } else if (layer.equals("postgres")) {	   
	    	SpellCardPostgres spellCard = mapperPostgres.map((SpellCardDTO) cardDTO, SpellCardPostgres.class);
		    spellCard.setId(id);		    
		    cardRepositoryPostgres.save(spellCard);
	    }	   
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
		if (layer.equals("mongo")) {		
		    TrapCardMongo trapCard = mapperMongo.map((TrapCardDTO) cardDTO, TrapCardMongo.class);
		    trapCard.setId(id);
		    cardRepositoryMongo.save(trapCard);
		    
	    } else if (layer.equals("postgres")) {	   
	    	TrapCardPostgres trapCard = mapperPostgres.map((TrapCardDTO) cardDTO, TrapCardPostgres.class);
		    trapCard.setId(id);		    
		    cardRepositoryPostgres.save(trapCard);
	    }
	}
	
	private CardDTO mapForFindAll(CardMongo card) {
		CardDTO cardDTO =null;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = mapperMongo.map((SpellCardMongo) card, SpellCardDTO.class);
	        	break;
	        case "Trap Card":
	        	cardDTO = mapperMongo.map((TrapCardMongo) card, TrapCardDTO.class);
	        	break;
	        case "Monster Card":
	        	cardDTO = mapperMongo.map((MonsterCardMongo) card, MonsterCardDTO.class);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
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
