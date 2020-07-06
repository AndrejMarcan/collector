package com.andy.collector.service;

import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.dto.UserDTO;
import com.andy.collector.repository.model.Card;
import com.andy.collector.repository.model.MonsterCard;
import com.andy.collector.repository.model.Note;
import com.andy.collector.repository.model.SpellCard;
import com.andy.collector.repository.model.TrapCard;
import com.andy.collector.repository.model.User;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class MapperService {
	private MapperFactory mapperFactory;
	private BoundMapperFacade<CardDTO, Card> cardBoundMapper;
	private BoundMapperFacade<SpellCardDTO, SpellCard> spellCardBoundMapper;
	private BoundMapperFacade<TrapCardDTO, TrapCard> trapCardBoundMapper;
	private BoundMapperFacade<MonsterCardDTO, MonsterCard> monsterCardBoundMapper;
	private BoundMapperFacade<NoteDTO, Note> noteBoundMapper;
	private BoundMapperFacade<UserDTO, User> userBoundMapper;
	
	MapperService(){
		this.mapperFactory = new DefaultMapperFactory.Builder().build();
		
		this.cardBoundMapper = mapperFactory.getMapperFacade(CardDTO.class, Card.class);
		this.spellCardBoundMapper = mapperFactory.getMapperFacade(SpellCardDTO.class, SpellCard.class);
		this.trapCardBoundMapper = mapperFactory.getMapperFacade(TrapCardDTO.class, TrapCard.class);
		this.monsterCardBoundMapper = mapperFactory.getMapperFacade(MonsterCardDTO.class, MonsterCard.class);
		this.noteBoundMapper = mapperFactory.getMapperFacade(NoteDTO.class, Note.class);
		this.userBoundMapper = mapperFactory.getMapperFacade(UserDTO.class, User.class);
	}
	
	public CardDTO mapForFindAll(Card card) {
		CardDTO cardDTO;
		
		switch(card.getCardType()) {
			case "Spell Card":
	        	cardDTO = getSpellCardBoundMapper().mapReverse((SpellCard) card);
	        	break;
	        case "Trap Card":
	        	cardDTO = getTrapCardBoundMapper().mapReverse((TrapCard) card);
	        	break;
	        case "Monster Card":
	        	cardDTO = getMonsterCardBoundMapper().mapReverse((MonsterCard) card);
	        	break;
	    	default:
	    		cardDTO = null;
		}	
		return cardDTO;
	}
	
	public MapperFactory getMapperFactory() {
		return mapperFactory;
	}

	public BoundMapperFacade<CardDTO, Card> getCardBoundMapper() {
		return cardBoundMapper;
	}

	public BoundMapperFacade<SpellCardDTO, SpellCard> getSpellCardBoundMapper() {
		return spellCardBoundMapper;
	}

	public BoundMapperFacade<TrapCardDTO, TrapCard> getTrapCardBoundMapper() {
		return trapCardBoundMapper;
	}

	public BoundMapperFacade<MonsterCardDTO, MonsterCard> getMonsterCardBoundMapper() {
		return monsterCardBoundMapper;
	}

	public BoundMapperFacade<NoteDTO, Note> getNoteBoundMapper() {
		return noteBoundMapper;
	}

	public BoundMapperFacade<UserDTO, User> getUserBoundMapper() {
		return userBoundMapper;
	}
	
}
