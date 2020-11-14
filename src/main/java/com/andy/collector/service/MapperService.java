package com.andy.collector.service;

import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.MonsterCardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.dto.SpellCardDTO;
import com.andy.collector.dto.TrapCardDTO;
import com.andy.collector.dto.UserDTO;
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
	private MapperFactory factoryPostgres;
	
	MapperService(){
		factoryPostgres = new DefaultMapperFactory.Builder().build();
		
		//postgres dao
		factoryPostgres.classMap(CardDTO.class, CardPostgres.class).byDefault().register();
		factoryPostgres.classMap(SpellCardDTO.class, SpellCardPostgres.class).byDefault().register();
		factoryPostgres.classMap(TrapCardDTO.class, TrapCardPostgres.class).byDefault().register();
		factoryPostgres.classMap(MonsterCardDTO.class, MonsterCardPostgres.class).byDefault().register();
		factoryPostgres.classMap(UserDTO.class, UserPostgres.class).byDefault().register();
		factoryPostgres.classMap(NoteDTO.class, NotePostgres.class).byDefault().register();
	}
	
	public MapperFacade getFacadePostgres() {
		return factoryPostgres.getMapperFacade();
	}
}
