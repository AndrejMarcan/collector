package com.andy.collector.service;

import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.dto.UserDTO;
import com.andy.collector.repository.mongo.model.CardMongo;
import com.andy.collector.repository.mongo.model.MonsterCardMongo;
import com.andy.collector.repository.mongo.model.NoteMongo;
import com.andy.collector.repository.mongo.model.SpellCardMongo;
import com.andy.collector.repository.mongo.model.TrapCardMongo;
import com.andy.collector.repository.mongo.model.UserMongo;
import com.andy.collector.repository.postgres.model.CardPostgres;
import com.andy.collector.repository.postgres.model.MonsterCardPostgres;
import com.andy.collector.repository.postgres.model.NotePostgres;
import com.andy.collector.repository.postgres.model.SpellCardPostgres;
import com.andy.collector.repository.postgres.model.TrapCardPostgres;
import com.andy.collector.repository.postgres.model.UserPostgres;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class MapperService {
	private MapperFactory factoryMongo;
	private MapperFactory factoryPostgres;
	
	MapperService(){
		factoryMongo = new DefaultMapperFactory.Builder().build();
		factoryPostgres = new DefaultMapperFactory.Builder().build();
		
		//mongo dao 
		factoryMongo.classMap(CardDTO.class, CardMongo.class).byDefault().register();
		factoryMongo.classMap(SpellCardDTO.class, SpellCardMongo.class).byDefault().register();
		factoryMongo.classMap(TrapCardDTO.class, TrapCardMongo.class).byDefault().register();
		factoryMongo.classMap(MonsterCardDTO.class, MonsterCardMongo.class).byDefault().register();
		factoryMongo.classMap(UserDTO.class, UserMongo.class).byDefault().register();
		factoryMongo.classMap(NoteDTO.class, NoteMongo.class).byDefault().register();
		
		//postgres dao
		factoryPostgres.classMap(CardDTO.class, CardPostgres.class).byDefault().register();
		factoryPostgres.classMap(SpellCardDTO.class, SpellCardPostgres.class).byDefault().register();
		factoryPostgres.classMap(TrapCardDTO.class, TrapCardPostgres.class).byDefault().register();
		factoryPostgres.classMap(MonsterCardDTO.class, MonsterCardPostgres.class).byDefault().register();
		factoryPostgres.classMap(UserDTO.class, UserPostgres.class).byDefault().register();
		factoryPostgres.classMap(NoteDTO.class, NotePostgres.class).byDefault().register();
	}

	public MapperFacade getFacadeMongo() {
		return factoryMongo.getMapperFacade();
	}
	
	public MapperFacade getFacadePostgres() {
		return factoryPostgres.getMapperFacade();
	}

	public MapperFactory getFactoryMongo() {
		return factoryMongo;
	}

	public void setFactoryMongo(MapperFactory factory) {
		this.factoryMongo = factory;
	}
	
	
}
