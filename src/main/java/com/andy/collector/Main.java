/*
 * Copyright (c) ...
 */
package com.andy.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.andy.collector.repository.UserRepository;

//import com.andy.collector.db.TableControls;

/**
 * Main class of the application. Creates new object of CardCollector class and turns it visible.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.andy.collector.*")
@ConfigurationPropertiesScan(basePackages = "main.*")
public class Main {
	
    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(Main.class);
     	app.run();
     	
     	
    }
    
}




















