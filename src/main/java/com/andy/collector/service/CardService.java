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

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	private MapperService mapperService;
	
	CardService(@Autowired MapperService mapperService){		
		mapperService.getMapperFactory().classMap(CardDTO.class, Card.class).byDefault().register();
		mapperService.getMapperFactory().classMap(SpellCardDTO.class, SpellCard.class).byDefault().register();
		mapperService.getMapperFactory().classMap(TrapCardDTO.class, TrapCard.class).byDefault().register();
		mapperService.getMapperFactory().classMap(MonsterCardDTO.class, MonsterCard.class).byDefault().register();
		
		this.mapperService = mapperService;
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
		BoundMapperFacade<SpellCardDTO, SpellCard> mapper = mapperService.getMapperFactory()
									.getMapperFacade(SpellCardDTO.class, SpellCard.class);
		Card card = mapper.map((SpellCardDTO) cardDTO);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewTrapCard(CardDTO cardDTO) {
		BoundMapperFacade<TrapCardDTO, TrapCard> mapper = mapperService.getMapperFactory()
									.getMapperFacade(TrapCardDTO.class, TrapCard.class);
		Card card = mapper.map((TrapCardDTO) cardDTO);
		    
		cardRepository.save(card);
	}
		
	//add new spell card to DB
	private void addNewMonsterCard(CardDTO cardDTO) {
		BoundMapperFacade<MonsterCardDTO, MonsterCard> mapper = mapperService.getMapperFactory()
									.getMapperFacade(MonsterCardDTO.class, MonsterCard.class);
		Card card = mapper.map((MonsterCardDTO) cardDTO);
		
		cardRepository.save(card);
	}
	
	//edit monster card details by ID
	private void editMonsterCard(CardDTO cardDTO, int id) {
		BoundMapperFacade<MonsterCardDTO, MonsterCard> mapper = mapperService.getMapperFactory()
									.getMapperFacade(MonsterCardDTO.class, MonsterCard.class);
	    MonsterCard monsterCard = mapper.map((MonsterCardDTO) cardDTO);
	    monsterCard.setId(id);
	    
	    cardRepository.save(monsterCard);
	}
	
	//edit spell card details by id
	private void editSpellCard(CardDTO cardDTO, int id) {
		BoundMapperFacade<SpellCardDTO, SpellCard> mapper = mapperService.getMapperFactory()
									.getMapperFacade(SpellCardDTO.class, SpellCard.class);
	    SpellCard spellCard = mapper.map((SpellCardDTO) cardDTO);
	    spellCard.setId(id);
	    
	    cardRepository.save(spellCard);
	}
	
	//edit trap card details by id
	private void editTrapCard(CardDTO cardDTO, int id) {
		BoundMapperFacade<TrapCardDTO, TrapCard> mapper = mapperService.getMapperFactory()
							.getMapperFacade(TrapCardDTO.class, TrapCard.class);
	    TrapCard trapCard = mapper.map((TrapCardDTO) cardDTO);
	    trapCard.setId(id);
	    
	    cardRepository.save(trapCard);
	}
	
	private CardDTO mapForFindAll(Card card) {
		CardDTO cardDTO;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = mapperService.getMapperFactory().getMapperFacade(SpellCardDTO.class, SpellCard.class).mapReverse((SpellCard) card);
	        	break;
	        case "Trap Card":
	        	cardDTO = mapperService.getMapperFactory().getMapperFacade(TrapCardDTO.class, TrapCard.class).mapReverse((TrapCard) card);
	        	break;
	        case "Monster Card":
	        	cardDTO = mapperService.getMapperFactory().getMapperFacade(MonsterCardDTO.class, MonsterCard.class).mapReverse((MonsterCard) card);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
	}
}
