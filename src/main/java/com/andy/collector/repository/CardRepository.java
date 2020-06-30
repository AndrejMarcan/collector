package com.andy.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.dto.CardDTO;

@Repository
public interface CardRepository extends JpaRepository<CardDTO, Integer>{
}
