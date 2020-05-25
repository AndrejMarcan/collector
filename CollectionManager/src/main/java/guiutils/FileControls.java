/*
 * Copyright (c) ...
 */
package main.java.guiutils;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The FileControl class provides methods to read text from files and to return full edition name
 * based on the short version of that name.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class FileControls {
    /* path to the text file with short name versions */
    private static String adrShorts = "D://Projects//project//CollectionManager//textFiles/Editions.txt";
    /* path to the text file with full name versions */
    private static String adrFullNames = "D://Projects//project/CollectionManager//textFiles/EditionsFullNames.txt";
    
    /* method readFromShortsFile returns ArrayList of short names from file */
    public static ArrayList<String> readFromShortsFile() {     
    	List<String> list = new ArrayList<>();		//create new list

			try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(adrShorts))) {
				/* bufferedReader returns as stream and convert it into a List */
				list = bufferedReader.lines().collect(Collectors.toList());
	            ArrayList<String> tmp = new ArrayList<String>(list);
	            return tmp;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    return null;
    }
    
    /* method readFromFullNamesFile returns ArrayList of full names from file */
    public static ArrayList<String> readFromFullNamesFile() {
    	java.util.List<String> list = new ArrayList<>();

		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(adrFullNames))) {
			/* bufferedReader returns as stream and convert it into a List */
			list = bufferedReader.lines().collect(Collectors.toList());
			ArrayList<String> tmp = new ArrayList<String>(list);
            return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /* Method getFullEditionName returns full name based on the short name */
    public static String getFullEditionName (String shortName) {
        ArrayList<String> shorts = readFromShortsFile();		//short versions of edition names
        ArrayList<String> fullNames = readFromFullNamesFile();	//full versions of edition names
        
        for (int i=0; i<shorts.size(); i++) {
            if(shorts.get(i).equalsIgnoreCase(shortName)){
                return fullNames.get(i);
            }
        }        
        return null;
    }
}





