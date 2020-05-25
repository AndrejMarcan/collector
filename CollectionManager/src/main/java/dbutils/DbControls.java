package main.java.dbutils;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.dal.Card;
import main.java.dal.MonsterCard;

public interface DbControls  {
	public boolean addCard(MonsterCard monsterCard) throws SQLException;
	public boolean addCard(Card card) throws SQLException;
	public boolean editCard(MonsterCard monsterCard, String cell) throws SQLException;
	public boolean editCard(Card card, String cell) throws SQLException;
	public boolean deleteCard(String cell) throws SQLException;
	public ArrayList<String> loadCardDetails(String cell) throws SQLException;
	public boolean addNotes(String cell, String text) throws SQLException;
	public String loadNotes(String cell) throws SQLException;
}
