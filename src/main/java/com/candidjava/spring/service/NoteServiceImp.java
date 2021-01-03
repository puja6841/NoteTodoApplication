package com.candidjava.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Note;
import com.candidjava.spring.repository.NoteRepository;


@Service
@Transactional
public class NoteServiceImp implements NoteService {
	@Autowired
	NoteRepository noteRepository;

	public void createNote(Note note) {
		// TODO Auto-generated method stub
		noteRepository.save(note);
	}

	public List<Note> getNote() {
		// TODO Auto-generated method stub
		return (List<Note>) noteRepository.findAll();
	}

	public Note findById(long id) {
		// TODO Auto-generated method stub
		return noteRepository.findOne(id);
	}

	public Note update(Note note, long l) {
		// TODO Auto-generated method stub
		//return userRepository.save(user);
		Note nte = findById(l);
		nte.setTitle(note.getTitle());
		nte.setDescription(note.getDescription());
		note.setId(note.getId());
		return noteRepository.save(nte);
	}

	public void deleteNoteById(long id) {
		// TODO Auto-generated method stub
		noteRepository.delete((long) id);
	}

	public Note updatePartially(Note note, long id) {
		// TODO Auto-generated method stub
		Note nte = findById(id);
		nte.setTitle(note.getTitle());
		nte.setDescription(note.getDescription());
		return noteRepository.save(nte);
	}

	
	


}
