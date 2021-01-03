package com.candidjava.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Note;
public interface NoteRepository extends CrudRepository<Note, Long>
{
	

}
