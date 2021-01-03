package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Todo;

public interface TodoService {
	public void createTodo(Todo todo);
	public List<Todo> getTodo();
	public Todo findById(long id);
	public Todo update(Todo todo, long l);
	public void deleteTodoById(long id);
	public Todo updatePartially(Todo todo, long id);
}
