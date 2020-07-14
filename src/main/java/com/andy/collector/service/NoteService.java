package com.andy.collector.service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.andy.collector.Main;
import com.andy.collector.dto.CardDTO;
import com.andy.collector.dto.NoteDTO;
import com.andy.collector.repository.mongo.NoteRepositoryMongo;
import com.andy.collector.repository.mongo.model.NoteDaoMongo;
import com.andy.collector.repository.postgres.NoteRepositoryPostgres;
import com.andy.collector.repository.postgres.model.NoteDaoPostgres;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;

/**
 * Service class for note objects. 
 * 
 * @version		0.1 14. July 2020
 * @author 		Andrej Marcan
 */

@Service
public class NoteService {
	
	@Value("${andy.database.picker}")
	private String layer; //try something else...
	
	@Autowired
	private NoteRepositoryMongo noteRepositoryMongo;
	
	@Autowired
	private NoteRepositoryPostgres noteRepositoryPostgres;
	
	@Autowired
	private CardService cardService;
	
	private MapperFacade mapperMongo;
	private MapperFacade mapperPostgres;
	
	public NoteService(@Autowired MapperService mapperService) {
		this.mapperMongo = mapperService.getFacadeMongo();
		this.mapperPostgres = mapperService.getFacadePostgres();
	}
	
	//add new note to card
	public void addNoteToCard(NoteDTO noteDTO, Integer id) {
		noteDTO.setIdCard(id);
		if (layer.equals("mongo")) {
			NoteDaoMongo note = mapperMongo.map(noteDTO, NoteDaoMongo.class);		
			CardDTO cardDTO = cardService.findCardById(id);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.add(noteDTO);	
			cardDTO.setId(id);
			cardDTO.setNotes(notesDTO);
			
			cardService.editCard(cardDTO, id);
			noteRepositoryMongo.save(note);
			
		} else if (layer.equals("postgres")) {
			noteDTO.setIdCard(id);
			CardDTO cardDTO = cardService.findCardById(id);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.add(noteDTO);	
			cardDTO.setId(id);
			cardDTO.setNotes(notesDTO);
			
			cardService.editCard(cardDTO, id);
		}
		
	}
	
	//edit note by id
	public void editNoteByIdCard(NoteDTO noteDTO, int idCard, int id) {	
		noteDTO.setId(id);
	    noteDTO.setIdCard(idCard);	
	    
	    if (layer.equals("mongo")) {
	    	CardDTO cardDTO = cardService.findCardById(idCard);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.remove(getNoteById(id, notesDTO));
			notesDTO.add(noteDTO);
			cardDTO.setId(idCard);
			cardDTO.setNotes(notesDTO);
			
			NoteDaoMongo note = mapperMongo.map(noteDTO, NoteDaoMongo.class);
			noteRepositoryMongo.save(note);
			cardService.editCard(cardDTO, idCard);
			
	    } else if (layer.equals("postgres")) {
	    	NoteDaoPostgres note = mapperPostgres.map(noteDTO, NoteDaoPostgres.class);
	 	    note.setIdNote(id);	   
	 	    
	 	    noteRepositoryPostgres.save(note);
	    }
	    
	}
	
	//delete note from card by id
	public void deleteNoteById(int id_card, int id_note) {
		
		if (layer.equals("mongo")) {
			CardDTO cardDTO = cardService.findCardById(id_card);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.remove(getNoteById(id_note, notesDTO));
			cardDTO.setNotes(notesDTO);
			
			noteRepositoryMongo.deleteById(id_note);
			cardService.editCard(cardDTO, id_card);		
			
	    } else if (layer.equals("postgres")) {
	    	
	    	CardDTO cardDTO = cardService.findCardById(id_card);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.remove(getNoteById(id_note, notesDTO));
			cardDTO.setNotes(notesDTO);
			
			cardService.editCard(cardDTO, id_card);	
	    }
		
		
	}
	
	//delete all notes from card
	public void deleteAllNotesFromCard(int id_card) {
		if (layer.equals("mongo")) {
			CardDTO cardDTO = cardService.findCardById(id_card);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.clear();
			cardDTO.setNotes(notesDTO);
			
			cardService.editCard(cardDTO, id_card);			
			
	    } else if (layer.equals("postgres")) {
	    	
	    	CardDTO cardDTO = cardService.findCardById(id_card);
			Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
			notesDTO.clear();
			cardDTO.setNotes(notesDTO);
			
			cardService.editCard(cardDTO, id_card);	
	    }
	}
	
	public void deleteAllNotesForCardId(int id_card) {
		CardDTO cardDTO = cardService.findCardById(id_card);
		Collection<NoteDTO> notesDTO = cardDTO.getNotes();	
		notesDTO.stream().forEach(p -> noteRepositoryMongo.deleteById(p.getId()));
	}
	
	//show note with id
	public NoteDTO showNote(Integer id) {
		NoteDTO noteDTO = null;
		if (layer.equals("mongo")) {	
			Optional<NoteDaoMongo> note = noteRepositoryMongo.findById(id);
			
			if(note.isPresent()) {
				noteDTO = mapperMongo.map(note.get(), NoteDTO.class);
			} else {
				return null;
			}
			
	    } else if (layer.equals("postgres")) {
	    	Optional<NoteDaoPostgres> note = noteRepositoryPostgres.findById(id);
			
			if(note.isPresent()) {
				noteDTO = mapperPostgres.map(note.get(), NoteDTO.class);
			} else {
				return null;
			}
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
