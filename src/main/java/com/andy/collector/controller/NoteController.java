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
import com.andy.collector.service.NoteService;

@RestController
@RequestMapping(value = "/note")
public class NoteController {

	@Autowired
	NoteService noteService;
	
	@PutMapping(value = "/update-note/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateCardNote(@RequestBody Note note, @PathVariable("id") String id) throws SQLException {
		noteService.editNoteByIdCard(note, Integer.valueOf(id));	
	}
	
	@DeleteMapping(value = "/delete-note/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCardNote(@PathVariable("id") String id) throws SQLException {
		noteService.deleteNoteByIdCard(Integer.valueOf(id));
	}

}
