package com.andy.collector.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.repository.mongo.model.NoteDaoMongo;

@Repository
public interface NoteRepositoryMongo extends MongoRepository<NoteDaoMongo, Integer>{
}
