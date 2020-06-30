package com.andy.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.dto.NoteDTO;

@Repository
public interface NoteRepository extends JpaRepository<NoteDTO, Integer>{
}
