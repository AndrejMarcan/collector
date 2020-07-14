/*
 * Copyright (c) ...
 */

package com.andy.collector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main class of the application. Creates new object of CardCollector class and turns it visible.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.andy.collector.*")
@EnableJpaRepositories(basePackages = "com.andy.collector.repository.postgres")
@EnableMongoRepositories(basePackages = "com.andy.collector.repository.mongo")
public class Main {
    public static void main(String[] args) throws Exception {	
    	SpringApplication app = new SpringApplication(Main.class);
    	app.setAddCommandLineProperties(true);
     	app.run(args);	
    }
}




















