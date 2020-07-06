package com.andy.collector.service;

import org.springframework.stereotype.Service;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class MapperService {
	private MapperFactory mapperFactory;
	
	MapperService(){
		this.mapperFactory = new DefaultMapperFactory.Builder().build();
	}

	public MapperFactory getMapperFactory() {
		return mapperFactory;
	}
	
	
}
