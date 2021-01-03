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

import com.candidjava.spring.bean.Note;
import com.candidjava.spring.service.NoteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value={"/note"})
public class NoteController {
	static Logger logger = Logger.getLogger(NoteController.class.getName());
	@Autowired
	NoteService noteService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> getNoteById(@PathVariable("id") long id) {
    	logger.log(Level.INFO, "In Note Controller:Fetching note with id " + id);
        Note note = noteService.findById(id);
        if (note == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Note>(note, HttpStatus.OK);
    }
    
    /**
     * 
     * @param note
     * @param ucBuilder
     * @return
     */
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createNote(@RequestBody Note note, UriComponentsBuilder ucBuilder){
		 logger.log(Level.INFO, "creating a note " + note.getDescription());
	     noteService.createNote(note);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(note.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
	 
	 /**
	  * 
	  * @return
	  */

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Note> getAllNote() {	 
		  List<Note> tasks=noteService.getNote();
		  return tasks;
	
	 }

	 /**
	  * 
	  * @param currentNote
	  * @return
	  */
	@PostMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateNote(@RequestBody Note currentNote)
	{
		Note note = noteService.findById(currentNote.getId());
		if (note ==null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		noteService.update(currentNote, currentNote.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Note> deleteNote(@PathVariable("id") long id){
		Note note = noteService.findById(id);
		if (note == null) {
			return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		}
		noteService.deleteNoteById(id);
		return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * 
	 * @param id
	 * @param currentNote
	 * @return
	 */
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Note> updateNotePartially(@PathVariable("id") long id, @RequestBody Note currentNote){
		Note note = noteService.findById(id);
		if(note ==null){
			return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		}
		Note nte =	noteService.updatePartially(currentNote, id);
		return new ResponseEntity<Note>(nte, HttpStatus.OK);
	}
}
