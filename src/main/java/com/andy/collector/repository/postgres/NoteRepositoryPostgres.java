package com.andy.collector.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.repository.postgres.model.NoteDaoPostgres;

@Repository
public interface NoteRepositoryPostgres extends JpaRepository<NoteDaoPostgres, Integer> {

}
