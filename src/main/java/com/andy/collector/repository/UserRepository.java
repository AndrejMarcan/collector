package com.andy.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
