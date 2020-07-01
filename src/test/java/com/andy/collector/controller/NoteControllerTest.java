package com.andy.collector.controller;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.dto.Note;
import com.andy.collector.service.NoteService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class NoteControllerTest {
	protected NoteController controller;
	protected Note note;
	
	@Autowired 
	NoteService noteService;
	
	@BeforeAll
	void init() {
		controller = new NoteController(noteService);
		note = new Note();
		note.setNote("Test note 1");
		
		//SpringApplication app = new SpringApplication(Main.class);
     	//app.run();
	}
	
	@Test
	@Order(1)
	public void testupdateCardNote() throws SQLException {
		ResponseEntity<String> ent = controller.updateNoteForCard(note, "52");
		assertEquals(new ResponseEntity<String>("note saved",HttpStatus.OK), ent);	
	}
	
	@Test
	@Order(2)
	public void testupdateCardNoteNOT() throws SQLException {
		ResponseEntity<String> ent = controller.updateNoteForCard(note, "5555555555");
		assertEquals(new ResponseEntity<String>(HttpStatus.NOT_FOUND), ent);	
	}
	
//	@Test
//	@Order(3)
//	public void testDeleteCardNote() throws SQLException {
//		ResponseEntity<String> ent = controller.deleteCardNote("202");
//		assertEquals(new ResponseEntity<String>("delete succesful",HttpStatus.OK), ent);	
//	}
//	
//	@Test
//	@Order(4)
//	public void testDeleteCardNoteNOT() throws SQLException {
//		ResponseEntity<String> ent = controller.deleteCardNote("202636363632727");
//		assertEquals(new ResponseEntity<String>(HttpStatus.NOT_FOUND), ent);	
//	}
	
	
}
