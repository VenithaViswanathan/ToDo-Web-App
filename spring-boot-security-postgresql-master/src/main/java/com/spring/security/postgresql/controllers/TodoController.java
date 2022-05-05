package com.spring.security.postgresql.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.postgresql.models.Todo;
import com.spring.security.postgresql.repository.TodoRepository;
import com.spring.security.postgresql.repository.UserRepository;

import antlr.StringUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class TodoController {
	
	@Autowired
	TodoRepository todoRepository;
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@PostMapping("/todos")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String saveTodo(@Valid @RequestBody Todo todo) {
		todoRepository.save(todo);
		return "todo saved";
	}

	@GetMapping("/todos")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Todo> getTodos() {
		List<Todo> todoList=todoRepository.findAll();
		return todoList;
	}

	@PutMapping("/todos/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String updateTodo(@PathVariable("id") String id,@RequestBody Todo todo) {
		
		Todo todoItem=todoRepository.findById(id);
		if( todoItem!= null) {
			todoItem.setDescription(todo.getDescription());
			todoItem.setName(todo.getName());
			todoItem.setStatus(todo.getStatus());
			todoItem.setUserId(todo.getUserId());
			todoRepository.save(todoItem);
			return "Todo updated";
		}
		
		return "Todo not found";
	}
	@DeleteMapping("/todos/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String deleteTodo(@PathVariable("id") String id) {
		Long todoId=Long.parseLong(id);
		Todo todoItem=todoRepository.findById(id);
		if( todoItem!= null) {
			todoRepository.deleteById(todoId);
			return "Todo deleted";
		}
		
		return "Todo not found";
	}
}
