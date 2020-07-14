package com.andy.collector.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.repository.mongo.model.CardDaoMongo;

@Repository
public interface CardRepositoryMongo extends MongoRepository<CardDaoMongo, Integer>{
}
