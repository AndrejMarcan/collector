/*
 * Copyright (c) ...
 */
package main.java.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The MyConnection class provides method to connect application to database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class MyConnection {
    
	/* Method getConnection provides connection to database */
    public static Connection getConnection() {
    	final String url= "jdbc:postgresql://localhost:5432/card_collector";
    	final String user = "postgres";
    	final String password = "root";
        Connection connection = null;	
        try {
        	Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            return connection;            
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }        
    }  
}




