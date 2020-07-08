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

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class MapperService {
	private MapperFactory factory;
	
	MapperService(){
		factory = new DefaultMapperFactory.Builder().build();
		
		factory.classMap(CardDTO.class, Card.class).byDefault().register();
		factory.classMap(SpellCardDTO.class, SpellCard.class).byDefault().register();
		factory.classMap(TrapCardDTO.class, TrapCard.class).byDefault().register();
		factory.classMap(MonsterCardDTO.class, MonsterCard.class).byDefault().register();
		factory.classMap(UserDTO.class, User.class).byDefault().register();
		factory.classMap(NoteDTO.class, Note.class).byDefault().register();
	}

	public MapperFacade getFacade() {
		return factory.getMapperFacade();
	}

	public MapperFactory getFactory() {
		return factory;
	}

	public void setFactory(MapperFactory factory) {
		this.factory = factory;
	}
	
	
}
