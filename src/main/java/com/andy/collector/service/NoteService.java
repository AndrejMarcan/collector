package com.andy.collector.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	CardService cardService;
	
	//edit note by id
	public void editNoteByIdCard(NoteDTO note, int id) {
		note.setIdNote(id);
		noteRepository.save(note);
	}
	
	//delete note
	public void deleteNoteById(int id) {
		noteRepository.deleteById(id);
	}
	
	//add new note to card
	public void addNoteToCard(NoteDTO note, Integer id) {
		CardDTO card = cardService.findCardById(id).get();
		Collection<NoteDTO> notes = card.getNotes();
		notes.add(note);
		card.setNotes(notes);
		card.setId(id);
		
		cardService.addNewCard(card);
		
	}

	public Optional<NoteDTO> showNote(Integer id) {
		return noteRepository.findById(id);		
	}
}
