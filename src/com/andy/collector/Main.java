/*
 * Copyright (c) ...
 */
package com.andy.collector;

import com.andy.collector.db.TableControls;

/**
 * Main class of the application. Creates new object of CardCollector class and turns it visible.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	TableControls dbControls = new TableControls();
    	try {
    		dbControls.createTableAlbum();
    		dbControls.createTableMonsterDetail();
        	dbControls.createTableNotes();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		throw new Exception("Table creation failed.");
    	}
    }
    
}




















