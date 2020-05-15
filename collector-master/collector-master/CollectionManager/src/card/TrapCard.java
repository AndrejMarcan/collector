/*
 * Copyright (c) ...
 */
package card;

import controls.AlbumCommands;
import controls.MyConnection;
import gui.AddTrap;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * The TrapCard class provides methods for adding a new trap card to database, edit data for trap card
 * in database and to get data for trap card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class TrapCard extends Card{
    private String type; //card type
    
    /* Constructor for TrapCard class */
    public TrapCard (String name, String rarity, String edition, String set,String language,
    				 String type) {
        super(name, rarity, edition, set, language);
        this.type = type;
    }
    
    /* Method adds trap card to database */
    @Override
    public void addCard() {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `album`(`name`, `rarity`, `edition`, `set`,"
                    + " `language`, `type`, `cardType`) VALUES (?,?,?,?,?,?,?)";

            try {
                preparedStatement = MyConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getRarity());
                preparedStatement.setString(3, getEdition());
                preparedStatement.setString(4, getSet());
                preparedStatement.setString(5, getLanguage());
                preparedStatement.setString(6, getType());
                preparedStatement.setString(7, "Trap card");

                if (preparedStatement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Card saved.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddTrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    
    /* Method for editing data of trap card in database */
    @Override
    public void editCard(String cell) {
        PreparedStatement preparedStatement;
        String query = "UPDATE `album` SET `name` ='"+getName()+"'"
        				+ ", `rarity` ='"+getRarity()+"'"
        				+ ", `edition` ='"+getEdition()+"'"
        				+ ", `set`='"+getSet()+"' "
        				+ ", `language`='"+getLanguage()+"'"
        				+ ", `type`='"+getType()+"'"
        				+ " WHERE `id`="+cell;
        
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            AlbumCommands.loadAlbum();
            JOptionPane.showMessageDialog(null, "Karta upravena");           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    /* Method shows informations about trap card in console */
    @Override
    public void getInfo() {
        System.out.println(super.getName());
        System.out.println(super.getRarity());
        System.out.println(super.getEdition());
        System.out.println(super.getSet());
        System.out.println(super.getLanguage());
        System.out.println(type);
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    
}








