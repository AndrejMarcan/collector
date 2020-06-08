/*
 * Copyright (c) ...
 */
package com.andy.collector.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * The MyConnection class provides method to connect application to database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@Component
public class MyConnection {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String user;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	/* Method getConnection provides connection to database */
    public Connection getConnection() {
        Connection connection = null;	
        try {
        	//Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            return connection;            
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }        
    }  
}




