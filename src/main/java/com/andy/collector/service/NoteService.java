package com.andy.collector.service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.repository.NoteRepository;
import com.andy.collector.repository.model.Note;

import ma.glasnost.orika.BoundMapperFacade;

@Service
public class NoteService {
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private CardService cardService;
	
	private BoundMapperFacade<NoteDTO, Note> mapper;
	
	public NoteService(@Autowired MapperService mapperService) {
		mapperService.getMapperFactory().classMap(NoteDTO.class, Note.class).byDefault().register();
		this.mapper = mapperService.getMapperFactory().getMapperFacade(NoteDTO.class, Note.class);
	}
	
	//add new note to card
	public void addNoteToCard(NoteDTO noteDTO, Integer id) {
		CardDTO cardDTO = cardService.findCardById(id);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.add(noteDTO);	
		cardDTO.setId(id);
		cardDTO.setNotes(notesDTO);
		
		cardService.editCard(cardDTO, id);
	}
	
	//edit note by id
	public void editNoteByIdCard(NoteDTO noteDTO, int id) {	    
	    Note note = mapper.map(noteDTO);
	    note.setIdNote(id);	   
	    
	    noteRepository.save(note);
	}
	
	//delete note from card by id
	public void deleteNoteById(int id_card, int id_note) {
		CardDTO cardDTO = cardService.findCardById(id_card);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.remove(getNoteById(id_note, notesDTO));
		cardDTO.setNotes(notesDTO);
		
		cardService.editCard(cardDTO, id_card);	
	}
	
	//delete all notes from card
	public void deleteAllNotesFromCard(int id_card) {
		CardDTO cardDTO = cardService.findCardById(id_card);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.clear();
		cardDTO.setNotes(notesDTO);
		
		cardService.editCard(cardDTO, id_card);		
	}
	
	//show note with id
	public NoteDTO showNote(Integer id) {
		Optional<Note> note = noteRepository.findById(id);
		
		if(note.isPresent()) {
			NoteDTO noteDTO = mapper.mapReverse(note.get());
			return noteDTO;
		} else {
			return null;
		}
	}
	
	private NoteDTO getNoteById(int id, Collection<NoteDTO> notes) {
		Predicate<NoteDTO> byId = p -> p.getIdNote()==id;
		return filterNote(byId, notes);
	}

	private NoteDTO filterNote(Predicate<NoteDTO> strategy, Collection<NoteDTO> notes) {
		return notes.stream().filter(strategy).findFirst().orElse(null);
	}
}
