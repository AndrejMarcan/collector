package com.andy.collector.repository;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public abstract class DbRepository  {
	String album = "album"; // table name for basic card info
	String monsterDetails = "monster_details"; // table name for monster details
	String notes = "notes"; // table name for notes
    
	public void dbCommit(Connection connection, boolean output) throws SQLException {
		if(connection != null) {
    		if(output) {
        		connection.commit();    
        	} else {
        		connection.rollback();    
        	}
    	}
	}
}
