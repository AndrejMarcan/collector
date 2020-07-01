package com.andy.collector.service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.Card;
import com.andy.collector.dto.Note;
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
	public void deleteNoteById(int id_card, int id_note) {
		Card card = cardService.findCardById(id_card).get();
		Collection<Note> notes = card.getNotes();
		notes.remove(getNoteById(id_note, notes));
		card.setNotes(notes);
		
		cardService.editCard(card, id_card);		
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
	
	private Note getNoteById(int id, Collection<Note> notes) {
		Predicate<Note> byId = p -> p.getIdNote()==id;
		return filterNote(byId, notes);
	}

	private Note filterNote(Predicate<Note> strategy, Collection<Note> notes) {
		return notes.stream().filter(strategy).findFirst().orElse(null);
	}
}
