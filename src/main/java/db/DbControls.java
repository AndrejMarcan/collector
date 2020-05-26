package main.java.db;

import java.sql.Connection;
import java.sql.SQLException;


public interface DbControls  {
	String album = "album"; // table name for basic card info
	String monsterDetails = "monster_details"; // table name for monster details
	String notes = "notes"; // table name for notes
    
	public void dbCommit(Connection connection, boolean output) throws SQLException;
}
