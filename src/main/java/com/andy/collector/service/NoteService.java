package com.andy.collector.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.model.Card;
import com.andy.collector.model.Note;
import com.andy.collector.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	CardService cardService;
	
	//edit note by id
	public void editNoteByIdCard(Note note, int id) {
		note.setIdNote(id);
		noteRepository.save(note);
	}
	
	//delete note
	public void deleteNoteById(int id) {
		noteRepository.deleteById(id);
	}
	
	//add new note to card
	public void addNoteToCard(Note note, Integer id) {
		Card card = cardService.findCardById(id).get();
		Collection<Note> notes = card.getNotes();
		notes.add(note);
		card.setNotes(notes);
		card.setId(id);
		
		cardService.addNewCard(card);
		
	}

	public Optional<Note> showNote(Integer id) {
		return noteRepository.findById(id);		
	}
}
