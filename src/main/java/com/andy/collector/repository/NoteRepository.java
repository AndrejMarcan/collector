package com.andy.collector.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.andy.collector.model.Note;

@Repository
public class NoteRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean addNote(Note note) {
		final String query = "INSERT INTO notes(id_card, note) VALUES (?, ?)";
		
		return jdbcTemplate.update(query, Integer.valueOf(note.getIdCard()), note.getNote()) > 0;
	}
	
	 /* Method addNotes is used for inserting notes for specific card by card ID */
    public boolean updateNote(String cell, Note note) throws SQLException {
    	final String query = "UPDATE notes SET note = '?' WHERE id_card = " + cell;  
        
        return jdbcTemplate.update(query, note.getNote())>0;
    }
    
    /* Method loadNotes fetches notes for specific card based on card ID */
    public String loadNotes(String cell) throws SQLException {
    	final String query = "SELECT * FROM notes WHERE id_card = " + cell;
        Note note = new Note();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for(Map<String, Object> row:rows) {
	  	    note.setNote((String) row.get("note"));    
	  	}
        return note.getNote();
    }
    
    public boolean deleteNote(String cell) throws SQLException {
    	final String query = "DELETE FROM notes WHERE id_card = " + cell; // cell represents table block where card ID is found
        
        return jdbcTemplate.update(query) > 0;
    }
}
