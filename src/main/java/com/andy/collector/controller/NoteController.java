package com.andy.collector.controller;

import java.sql.SQLException;
import java.util.Optional;

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

import com.andy.collector.dto.NoteDTO;
import com.andy.collector.service.NoteService;

@RestController
@RequestMapping(value = "/note")
public class NoteController {
	private NoteService noteService;
	
	NoteController(@Autowired NoteService noteService) {
		this.noteService = noteService;
	}
	
	@PutMapping(value = "/update-note/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCardNote(@RequestBody NoteDTO note, @PathVariable("id") String id) throws SQLException {			
		try {
			noteService.editNoteByIdCard(note, Integer.valueOf(id));
			return new ResponseEntity<String>("note saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delete-note/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCardNote(@PathVariable("id") String id) throws SQLException {	
		try {
			noteService.deleteNoteById(Integer.valueOf(id));
			return new ResponseEntity<String>("delete succesful",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNoteToCard(@PathVariable("id") String id, @RequestBody NoteDTO note) throws SQLException {
		try {
			noteService.addNoteToCard(note, Integer.valueOf(id));
			return new ResponseEntity<String>("note saved",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("card not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDTO> showNote(@PathVariable("id") String id) throws SQLException {
		Optional<NoteDTO> note = noteService.showNote(Integer.valueOf(id));
		if(note.isPresent()) {
			return new ResponseEntity<NoteDTO> (note.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<NoteDTO> (HttpStatus.NOT_FOUND);
		}
	}
}
