/*
 * Copyright (c) ...
 */
package controls;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * The MyConnection class provides method to connect application to database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class MyConnection {
    
	/* Method getConnection provides connection to database */
    public static Connection getConnection() {
        Connection con = null;	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cardcollectordb"
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"   //deals with time-zone problem
                    + "&serverTimezone=UTC", "root", "");
            return con;            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }        
    }  
}




