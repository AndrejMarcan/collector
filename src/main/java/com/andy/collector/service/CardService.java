package com.andy.collector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.andy.collector.Main;
import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.repository.mongo.CardRepositoryMongo;
import com.andy.collector.repository.mongo.NoteRepositoryMongo;
import com.andy.collector.repository.mongo.model.CardDaoMongo;
import com.andy.collector.repository.mongo.model.MonsterCardDaoMongo;
import com.andy.collector.repository.mongo.model.SpellCardDaoMongo;
import com.andy.collector.repository.mongo.model.TrapCardDaoMongo;
import com.andy.collector.repository.postgres.CardRepositoryPostgres;
import com.andy.collector.repository.postgres.model.CardDaoPostgres;
import com.andy.collector.repository.postgres.model.MonsterCardDaoPostgres;
import com.andy.collector.repository.postgres.model.SpellCardDaoPostgres;
import com.andy.collector.repository.postgres.model.TrapCardDaoPostgres;

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
	
	@Autowired
	private CardRepositoryMongo cardRepositoryMongo;
	
	@Autowired
	private CardRepositoryPostgres cardRepositoryPostgres;
	
	CardService(@Autowired MapperService mapperService) {
		this.mapperMongo = mapperService.getFacadeMongo();
		this.mapperPostgres = mapperService.getFacadePostgres();
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
			List<CardDaoMongo> cards = cardRepositoryMongo.findAll();
			cardsDTO = cards.stream().map(p -> mapForFindAll(p)).collect(Collectors.toList());
	    } else if (layer.equals("postgres")) {	   
	    	List<CardDaoPostgres> cards = cardRepositoryPostgres.findAll();
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
			Optional<CardDaoMongo> cardOpt = cardRepositoryMongo.findById(id);
			
			if(cardOpt.isPresent()) {		
				CardDaoMongo card = cardOpt.get();
				cardDTO = mapForFindAll(card);
			} else {
				return null;
			}
	    } else if (layer.equals("postgres")) {	   
	    	Optional<CardDaoPostgres> cardOpt = cardRepositoryPostgres.findById(id);
			
			if(cardOpt.isPresent()) {		
				CardDaoPostgres card = cardOpt.get();
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
			CardDaoMongo card = mapperMongo.map((SpellCardDTO) cardDTO, SpellCardDaoMongo.class);    
			cardRepositoryMongo.save(card);
	    } else if (layer.equals("postgres")) {	   
	    	CardDaoPostgres card = mapperPostgres.map((SpellCardDTO) cardDTO, SpellCardDaoPostgres.class);    
			cardRepositoryPostgres.save(card);
	    }
		
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {
		if (layer.equals("mongo")) {		
			CardDaoMongo card = mapperMongo.map((TrapCardDTO) cardDTO, TrapCardDaoMongo.class);    
			cardRepositoryMongo.save(card);
	    } else if (layer.equals("postgres")) {	   
	    	CardDaoPostgres card = mapperPostgres.map((TrapCardDTO) cardDTO, TrapCardDaoPostgres.class);    
			cardRepositoryPostgres.save(card);
	    }	
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {
		if (layer.equals("mongo")) {		
			CardDaoMongo card = mapperMongo.map((MonsterCardDTO) cardDTO, MonsterCardDaoMongo.class);
			cardRepositoryMongo.save(card);
	    } else if (layer.equals("postgres")) {	   
	    	CardDaoPostgres card = mapperPostgres.map((MonsterCardDTO) cardDTO, MonsterCardDaoPostgres.class);    
			cardRepositoryPostgres.save(card);
	    }
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {
		if (layer.equals("mongo")) {		
			MonsterCardDaoMongo monsterCard = mapperMongo.map((MonsterCardDTO) cardDTO, MonsterCardDaoMongo.class);
		    monsterCard.setId(id);		    
		    cardRepositoryMongo.save(monsterCard);
		    
	    } else if (layer.equals("postgres")) {	   
	    	MonsterCardDaoPostgres monsterCard = mapperPostgres.map((MonsterCardDTO) cardDTO, MonsterCardDaoPostgres.class);
		    monsterCard.setId(id);		    
		    cardRepositoryPostgres.save(monsterCard);
	    }
	    
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
		if (layer.equals("mongo")) {		
			 SpellCardDaoMongo spellCard = mapperMongo.map((SpellCardDTO) cardDTO, SpellCardDaoMongo.class);
			 spellCard.setId(id);
			 cardRepositoryMongo.save(spellCard);
			 
	    } else if (layer.equals("postgres")) {	   
	    	SpellCardDaoPostgres spellCard = mapperPostgres.map((SpellCardDTO) cardDTO, SpellCardDaoPostgres.class);
		    spellCard.setId(id);		    
		    cardRepositoryPostgres.save(spellCard);
	    }	   
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
		if (layer.equals("mongo")) {		
		    TrapCardDaoMongo trapCard = mapperMongo.map((TrapCardDTO) cardDTO, TrapCardDaoMongo.class);
		    trapCard.setId(id);
		    cardRepositoryMongo.save(trapCard);
		    
	    } else if (layer.equals("postgres")) {	   
	    	TrapCardDaoPostgres trapCard = mapperPostgres.map((TrapCardDTO) cardDTO, TrapCardDaoPostgres.class);
		    trapCard.setId(id);		    
		    cardRepositoryPostgres.save(trapCard);
	    }
	}
	
	private CardDTO mapForFindAll(CardDaoMongo card) {
		CardDTO cardDTO =null;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = mapperMongo.map((SpellCardDaoMongo) card, SpellCardDTO.class);
	        	break;
	        case "Trap Card":
	        	cardDTO = mapperMongo.map((TrapCardDaoMongo) card, TrapCardDTO.class);
	        	break;
	        case "Monster Card":
	        	cardDTO = mapperMongo.map((MonsterCardDaoMongo) card, MonsterCardDTO.class);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
	}
	
	private CardDTO mapForFindAllPostgres(CardDaoPostgres card) {
		CardDTO cardDTO =null;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = mapperPostgres.map((SpellCardDaoPostgres) card, SpellCardDTO.class);
	        	break;
	        case "Trap Card":
	        	cardDTO = mapperPostgres.map((TrapCardDaoPostgres) card, TrapCardDTO.class);
	        	break;
	        case "Monster Card":
	        	cardDTO = mapperPostgres.map((MonsterCardDaoPostgres) card, MonsterCardDTO.class);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
	}
}
