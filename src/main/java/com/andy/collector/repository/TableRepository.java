package com.andy.collector.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void createTableAlbum() throws Exception {
		final String query = "CREATE TABLE IF NOT EXISTS public.album"
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
		
		jdbcTemplate.execute(query);
	}

	public void createTableMonsterDetail() throws Exception {
		final String query = "CREATE TABLE IF NOT EXISTS public.monster_details" 
					+"( id_monster bigint NOT NULL,"
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
		
		jdbcTemplate.execute(query);
	}
	
	public void createTableNotes() throws Exception {
		final String query = "CREATE TABLE IF NOT EXISTS public.notes" 
					+"( id_card bigint NOT NULL,"
					+ "	note text NOT NULL,"
					+ " CONSTRAINT id_card FOREIGN KEY (id_card)"
					+ " REFERENCES public.album (id) MATCH SIMPLE"
					+ " ON UPDATE CASCADE"
					+ " ON DELETE CASCADE"
					+ " NOT VALID );"
					+ " ALTER TABLE public.notes OWNER to postgres;";
		
		jdbcTemplate.execute(query);
	}
	
	public void createTableUsers() {
		final String query = "CREATE TABLE IF NOT EXISTS public.users (id_user bigint NOT NULL GENERATED ALWAYS AS IDENTITY "
				+ "( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 987654321 ),"
				+ " nickname text NOT NULL, password text NOT NULL,"
				+ " PRIMARY KEY (id_user) );"
				+ "ALTER TABLE public.album OWNER to postgres;";
		jdbcTemplate.execute(query);
	}
	
}
