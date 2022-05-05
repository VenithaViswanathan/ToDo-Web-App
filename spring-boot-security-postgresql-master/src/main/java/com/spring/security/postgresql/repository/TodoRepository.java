package com.spring.security.postgresql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.postgresql.models.Todo;
import com.spring.security.postgresql.models.User;

@Repository
public  interface TodoRepository extends JpaRepository<Todo, Long> {
	Todo findById(String id);
	
}
