package com.andy.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer>{
}
