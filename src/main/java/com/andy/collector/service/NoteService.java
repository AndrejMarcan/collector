package com.andy.collector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.model.Note;
import com.andy.collector.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	NoteRepository noteRepository;
	
	public void editNoteByIdCard(Note note, int id) {
		note.setIdNote(id+1);
		noteRepository.save(note);
	}
	
	public void deleteNoteByIdCard(int id) {
		Note note = new Note();
		note.setIdNote(id+1);
		note.setNote("");
		noteRepository.save(note);
	}
}
