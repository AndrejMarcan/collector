package com.andy.collector.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.model.Card;
import com.andy.collector.model.Note;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class NoteServiceTest {

	@Autowired
	NoteService ns;
	
	@Autowired
	CardService cs;
	
	protected static Note note1;
	protected static Note note2;
	
	@BeforeAll
	public void init() {
		note1 = new Note();
		note2 = new Note();
		
		note1.setNote("Test note 1");
		note2.setNote("Test note 2");
		
		//SpringApplication app = new SpringApplication(Main.class);
     	//app.run();
	}
	
	@Test
	public void testEditNoteByIdCardMethod() {
		
		ns.editNoteByIdCard(note2, 53);
		
		Card card = cs.findCardById(53).get();
		Note noteAfter = card.getNote();
		
		assertEquals(note2.getNote(), noteAfter.getNote());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {53, 54, 55})
	public void testEditNoteByIdCardMethod(int number) {
		
		ns.editNoteByIdCard(note1, number);
		
		Card card = cs.findCardById(number).get();
		Note noteAfter = card.getNote();
		
		assertEquals(note1.getNote(), noteAfter.getNote());
	}

	@ParameterizedTest
	@ValueSource(ints = {53, 54, 55})
	public void testDeleteNoteByIdCardMethod(int number) {
		
		ns.deleteNoteByIdCard(number);
		
		Card card = cs.findCardById(number).get();
		Note noteAfter = card.getNote();
		
		assertEquals("", noteAfter.getNote());
	}
}
