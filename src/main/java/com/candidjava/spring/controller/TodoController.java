package com.candidjava.spring.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.candidjava.spring.bean.Todo;
import com.candidjava.spring.service.TodoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value={"/todo"})
public class TodoController {
	static Logger logger = Logger.getLogger(TodoController.class.getName());

	@Autowired
	TodoService todoService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id) {
    	logger.log(Level.INFO, "In todo Controller:Fetching todo with id " + id);
        Todo todo = todoService.findById(id);
        if (todo == null) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }
    
    /**
     * 
     * @param todo
     * @param ucBuilder
     * @return
     */
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createTodo(@RequestBody Todo todo, UriComponentsBuilder ucBuilder){
	     //System.out.println("Creating User "+todo.getTasktitle());
		 logger.log(Level.INFO, "In Note Controller:Fetching note with id " + todo.getTasktitle());
	     todoService.createTodo(todo);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(todo.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 /**
	  * 
	  * @return
	  */
	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Todo> getTodo() {	 
		  List<Todo> tasks=todoService.getTodo();
		  return tasks;
	
	 }

	 /**
	  * 
	  * @param currentTodo
	  * @return
	  */
	@PostMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateTodo(@RequestBody Todo currentTodo)
	{
		Todo todo = todoService.findById(currentTodo.getId());
		if (todo==null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		todoService.update(currentTodo, currentTodo.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Todo> deleteTodo(@PathVariable("id") long id){
		Todo todo = todoService.findById(id);
		if (todo == null) {
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
		todoService.deleteTodoById(id);
		return new ResponseEntity<Todo>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * 
	 * @param id
	 * @param currentTodo
	 * @return
	 */
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Todo> updateTodoPartially(@PathVariable("id") long id, @RequestBody Todo currentTodo){
		Todo todo = todoService.findById(id);
		if(todo ==null){
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
		Todo usr =	todoService.updatePartially(currentTodo, id);
		return new ResponseEntity<Todo>(usr, HttpStatus.OK);
	}
}
