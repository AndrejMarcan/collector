package com.andy.collector.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.collector.model.Note;
import com.andy.collector.repository.NoteRepository;

@RestController
@RequestMapping(value = "/note")
public class NoteController {
	private final NoteRepository noteRepository;
	
	NoteController(@Autowired NoteRepository noteRepository){
		this.noteRepository = noteRepository;
	}
	
	@PostMapping(value = "/add-note/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addCardNote(@RequestBody Note note, @PathVariable("id") String id) throws SQLException {
		boolean notes = noteRepository.addNote(note);
		
		if(notes) {
			return new ResponseEntity<Boolean>(notes,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(notes,HttpStatus.NOT_FOUND);
		}		
	}
	
	@GetMapping(value = "/load-note/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loadCardNote(@PathVariable("id") String id) throws SQLException {
		String notes = noteRepository.loadNotes(id);
		
		if(notes != null) {
			return new ResponseEntity<String>(notes,HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(notes,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(value = "/update-note/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateCardNote(@RequestBody Note note, @PathVariable("id") String id) throws SQLException {
		boolean notes = noteRepository.updateNote(id, note);
		
		if(notes) {
			return new ResponseEntity<Boolean>(notes,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(notes,HttpStatus.NOT_FOUND);
		}		
	}
	
	@DeleteMapping(value = "/delete-note/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteCardNote(@PathVariable("id") String id) throws SQLException {
		boolean notes = noteRepository.deleteNote(id);
		
		if(notes) {
			return new ResponseEntity<Boolean>(notes,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(notes,HttpStatus.NOT_FOUND);
		}		
	}
}
