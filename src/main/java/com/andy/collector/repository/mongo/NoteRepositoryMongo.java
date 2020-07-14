package com.andy.collector.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.repository.mongo.model.NoteMongo;

@Repository
public interface NoteRepositoryMongo extends MongoRepository<NoteMongo, Integer>{
}
