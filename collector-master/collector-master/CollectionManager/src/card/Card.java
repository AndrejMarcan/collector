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
public abstract class Card {
    private String name;		//card name
    private Rarities rarity;		//card rarity
    private String edition;		//card edition
    private String set;			//card set
    private String language;	//card language
    
    /* Constructor for Card class */
    public Card (String name, Rarities rarity, String edition, String set, String language) { 
        this.name = name;
        this.rarity = rarity;
        this.edition = edition;
        this.set = set;
        this.language = language;
    }
    
    public abstract void addCard();
    
    public abstract void editCard(String cell);
    
    public abstract void getInfo();
    
    public String getName() {
        return name;
    }
    
    public String getRarity() {
        return rarity.getRarity();
    }
    
    public String getEdition() {
        return edition;
    }
    
    public String getSet() {
        return set;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRarity(Rarities rarity) {
        this.rarity = rarity;
    }
    
    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    public void setSet(String set) {
        this.set = set;
    }
    
    public void setLanguage(String language) {
        this.language=language;
    }

    
    
}

















