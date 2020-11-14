package com.andy.collector.service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.repository.postgres.NoteRepositoryPostgres;
import com.andy.collector.repository.postgres.model.NotePostgres;

import ma.glasnost.orika.MapperFacade;

/**
 * Service class for note objects. 
 * 
 * @version		0.1 14. July 2020
 * @author 		Andrej Marcan
 */

@Service
public class NoteService {
	private static final Logger LOG = LoggerFactory.getLogger(CardService.class);
	
	@Autowired
	private NoteRepositoryPostgres noteRepositoryPostgres;
	
	@Autowired
	private CardService cardService;
	private MapperFacade mapperPostgres;
	
	public NoteService(@Autowired MapperService mapperService) {
		this.mapperPostgres = mapperService.getFacadePostgres();
	}
	
	//add new note to card
	public void addNoteToCard(NoteDTO noteDTO, Integer id) {
		LOG.info("Trying to add new note to card by it's ID.");
		
		noteDTO.setIdCard(id);
		noteDTO.setIdCard(id);
		CardDTO cardDTO = cardService.findCardById(id);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.add(noteDTO);	
		cardDTO.setId(id);
		cardDTO.setNotes(notesDTO);
			
		cardService.editCard(cardDTO, id);
	}
	
	public void editNoteByIdCard(NoteDTO noteDTO, int idCard, int id) {	
		LOG.info("Trying to edit note on card by their IDs.");
		
		noteDTO.setId(id);
	    noteDTO.setIdCard(idCard);
	    NotePostgres note = mapperPostgres.map(noteDTO, NotePostgres.class);
	 	note.setId(id);	   
	 	    
	 	 noteRepositoryPostgres.save(note);
	}

	public void deleteNoteById(int id_card, int id_note) {
		LOG.info("Trying to delete note form card.");
	    	
	    CardDTO cardDTO = cardService.findCardById(id_card);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.remove(getNoteById(id_note, notesDTO));
		cardDTO.setNotes(notesDTO);
			
		cardService.editCard(cardDTO, id_card);	
	}
	
	//delete all notes from card
	public void deleteAllNotesFromCard(int id_card) {
		LOG.info("Trying to delete all notes from card.");
	    	
	    CardDTO cardDTO = cardService.findCardById(id_card);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.clear();
		cardDTO.setNotes(notesDTO);
			
		cardService.editCard(cardDTO, id_card);	
	}
	
	public NoteDTO showNote(Integer id) {
		LOG.info("Trying to get note by its ID {} " + id);
		
		NoteDTO noteDTO = null;
	    Optional<NotePostgres> note = noteRepositoryPostgres.findById(id);
			
		if(note.isPresent()) {
			noteDTO = mapperPostgres.map(note.get(), NoteDTO.class);
		} else {
			return null;
		}
		return noteDTO;
	}
	
	private NoteDTO getNoteById(int id, Collection<NoteDTO> notes) {
		Predicate<NoteDTO> byId = p -> p.getId()==id;
		return filterNote(byId, notes);
	}
	

	private NoteDTO filterNote(Predicate<NoteDTO> strategy, Collection<NoteDTO> notes) {
		return notes.stream().filter(strategy).findFirst().orElse(null);
	}
	
}
