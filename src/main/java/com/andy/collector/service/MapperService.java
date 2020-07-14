package com.andy.collector.service;

import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.dto.UserDTO;
import com.andy.collector.repository.mongo.model.CardDaoMongo;
import com.andy.collector.repository.mongo.model.MonsterCardDaoMongo;
import com.andy.collector.repository.mongo.model.NoteDaoMongo;
import com.andy.collector.repository.mongo.model.SpellCardDaoMongo;
import com.andy.collector.repository.mongo.model.TrapCardDaoMongo;
import com.andy.collector.repository.mongo.model.UserDaoMongo;
import com.andy.collector.repository.postgres.model.CardDaoPostgres;
import com.andy.collector.repository.postgres.model.MonsterCardDaoPostgres;
import com.andy.collector.repository.postgres.model.NoteDaoPostgres;
import com.andy.collector.repository.postgres.model.SpellCardDaoPostgres;
import com.andy.collector.repository.postgres.model.TrapCardDaoPostgres;
import com.andy.collector.repository.postgres.model.UserDaoPostgres;

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
		factoryMongo.classMap(CardDTO.class, CardDaoMongo.class).byDefault().register();
		factoryMongo.classMap(SpellCardDTO.class, SpellCardDaoMongo.class).byDefault().register();
		factoryMongo.classMap(TrapCardDTO.class, TrapCardDaoMongo.class).byDefault().register();
		factoryMongo.classMap(MonsterCardDTO.class, MonsterCardDaoMongo.class).byDefault().register();
		factoryMongo.classMap(UserDTO.class, UserDaoMongo.class).byDefault().register();
		factoryMongo.classMap(NoteDTO.class, NoteDaoMongo.class).byDefault().register();
		
		//postgres dao
		factoryPostgres.classMap(CardDTO.class, CardDaoPostgres.class).byDefault().register();
		factoryPostgres.classMap(SpellCardDTO.class, SpellCardDaoPostgres.class).byDefault().register();
		factoryPostgres.classMap(TrapCardDTO.class, TrapCardDaoPostgres.class).byDefault().register();
		factoryPostgres.classMap(MonsterCardDTO.class, MonsterCardDaoPostgres.class).byDefault().register();
		factoryPostgres.classMap(UserDTO.class, UserDaoPostgres.class).byDefault().register();
		factoryPostgres.classMap(NoteDTO.class, NoteDaoPostgres.class).byDefault().register();
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
