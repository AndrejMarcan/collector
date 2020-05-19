/*
 * Copyright (c) ...
 */
package card;

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
    public TrapCard (String name, Rarities rarity, Editions edition, String set,String language,
    				 String type) {
        super(name, rarity, edition, set, language);
        this.type = type;
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








