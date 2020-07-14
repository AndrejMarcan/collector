package com.andy.collector.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andy.collector.repository.postgres.model.UserPostgres;

@Repository
public interface UserRepositoryPostgres extends JpaRepository<UserPostgres, Integer>{

}
