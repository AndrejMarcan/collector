/*
 * Copyright (c) ...
 */
package main.java.com.andy.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//import com.andy.collector.db.TableControls;

/**
 * Main class of the application. Creates new object of CardCollector class and turns it visible.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@SpringBootApplication
@ComponentScan(basePackages = "main.java.com.andy.collector.*")
public class Main {

    public static void main(String[] args) throws Exception {
       	SpringApplication app = new SpringApplication(Main.class);
        app.run();
    	
//    	TableControls dbControls = new TableControls();
//    	try {
//    		dbControls.createTableAlbum();
//    		dbControls.createTableMonsterDetail();
//        	dbControls.createTableNotes();
//    	} catch (Exception ex) {
//    		ex.printStackTrace();
//    		throw new Exception("Table creation failed.");
//    	}
    }
    
}




















