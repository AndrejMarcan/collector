package main.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableControls implements DbControls{
	public void createTableAlbum() throws Exception {
		String query = "CREATE TABLE IF NOT EXISTS public.album "
				+ "(id bigint NOT NULL GENERATED ALWAYS AS IDENTITY "
				+ "( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 987654321 ),"
				+ " name text NOT NULL,"
				+ " set text NOT NULL,"
				+ " edition text NOT NULL,"
				+ " language text NOT NULL,"
				+ " card_type text NOT NULL,"
				+ " rarity text NOT NULL,"
				+ " type text NOT NULL,"
				+ " PRIMARY KEY (id) ); "
				+ "ALTER TABLE public.album OWNER to postgres;";

		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareCall(query); ) {
			boolean output = false;	
			conn.setAutoCommit(false);

			try {
				ps.execute();
				output = true;
			} catch(SQLException ex) {
				ex.printStackTrace();
				throw new SQLException("CREATE TABLE album SQL unsuccessful");
			} finally {
	    		dbCommit(conn, output);                	
	    	}
			conn.setAutoCommit(true);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("CREATE TABLE album unsuccessful");
		}
	}

	public void createTableMonsterDetail() throws Exception {
		String query = "CREATE TABLE IF NOT EXISTS public.monster_details" +
					"( id_monster bigint NOT NULL,"
					+ "	summ_method text NOT NULL,"
					+ " attribute text NOT NULL,"
					+ " level text NOT NULL, "
					+ " atk text NOT NULL,"
					+ "	def text NOT NULL,"
					+ " CONSTRAINT id_monster FOREIGN KEY (id_monster)"
					+ " REFERENCES public.album (id) MATCH SIMPLE"
					+ " ON UPDATE CASCADE"
					+ " ON DELETE CASCADE"
					+ " NOT VALID );"
					+ " ALTER TABLE public.monster_details OWNER to postgres;";
	
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareCall(query); ) {
			boolean output = false;
			conn.setAutoCommit(false);

			try {
				ps.execute();
				output = true;
			} catch(SQLException ex) {
				ex.printStackTrace();
				throw new SQLException("CREATE TABLE monster details SQL unsuccessful");
			} finally {
	    		dbCommit(conn, output);                	
	    	}
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("CREATE TABLE monster_details unsuccessful");
		} 
	}
	
	public void createTableNotes() throws Exception {
		String query = "CREATE TABLE IF NOT EXISTS public.notes" +
					"( id_card bigint NOT NULL,"
					+ "	note text NOT NULL,"
					+ " CONSTRAINT id_card FOREIGN KEY (id_card)"
					+ " REFERENCES public.album (id) MATCH SIMPLE"
					+ " ON UPDATE CASCADE"
					+ " ON DELETE CASCADE"
					+ " NOT VALID );"
					+ " ALTER TABLE public.notes OWNER to postgres;";
	
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareCall(query); ) {
			boolean output = false;
			conn.setAutoCommit(false);

			try {
				ps.execute();
				output = true;
			} catch(SQLException ex) {
				ex.printStackTrace();
				throw new SQLException("CREATE TABLE notes SQL unsuccessful");
			} finally {
	    		dbCommit(conn, output);                	
	    	}
			conn.setAutoCommit(true);
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("CREATE TABLE notes unsuccessful");
		}
	}
	
	@Override
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
