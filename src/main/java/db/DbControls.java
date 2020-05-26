package main.java.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbControls  {
	public void dbCommit(Connection connection, boolean output) throws SQLException;
}
