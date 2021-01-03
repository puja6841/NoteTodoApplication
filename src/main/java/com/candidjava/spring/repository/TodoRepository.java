package com.candidjava.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Todo;
public interface TodoRepository extends CrudRepository<Todo, Long>
{
	

}
