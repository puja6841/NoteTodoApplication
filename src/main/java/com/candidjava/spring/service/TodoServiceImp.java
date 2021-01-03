package com.candidjava.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.candidjava.spring.bean.Todo;
import com.candidjava.spring.repository.TodoRepository;

@Service
@Transactional
public class TodoServiceImp implements TodoService {
	@Autowired
	TodoRepository todoRepository;

	public void createTodo(Todo todo) {
		// TODO Auto-generated method stub
		todoRepository.save(todo);
	}

	public List<Todo> getTodo() {
		// TODO Auto-generated method stub
		return (List<Todo>) todoRepository.findAll();
	}

	public Todo findById(long id) {
		// TODO Auto-generated method stub
		return todoRepository.findOne(id);
	}

	public Todo update(Todo todo, long l) {
		// TODO Auto-generated method stub
		//return userRepository.save(user);
		Todo tdr = findById(l);
		tdr.setTasktitle(todo.getTasktitle());
		
		todo.setId(todo.getId());
		return todoRepository.save(tdr);
	}

	public void deleteTodoById(long id) {
		// TODO Auto-generated method stub
		todoRepository.delete((long) id);
	}

	public Todo updatePartially(Todo todo, long id) {
		// TODO Auto-generated method stub
		Todo tdr = findById(id);
		tdr.setTasktitle(todo.getTasktitle());
		return todoRepository.save(tdr);
	}

	
	
	


}
