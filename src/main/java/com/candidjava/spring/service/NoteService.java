package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Note;

public interface NoteService {
	public void createNote(Note note);
	public List<Note> getNote();
	public Note findById(long id);
	public Note update(Note note, long l);
	public void deleteNoteById(long id);
	public Note updatePartially(Note note, long id);
}
