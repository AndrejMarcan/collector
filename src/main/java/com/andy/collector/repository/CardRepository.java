package com.andy.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
}
