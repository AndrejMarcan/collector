/*
 * Copyright (c) ...
 */
package card;

/**
 * The MonsterCard class provides methods for adding a new monster card to database,
 * edit data for monster card in database and to get data for monster card from the database.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class MonsterCard extends Card {
    private String type;		//monster type
    private String summMethod;	//summoning method
    private String attribute;	//monster attribute
    private String atk;			//attack points
    private String def;			//defense points
    private String level;		//monster level
    
    /* Constructor for MonsterCard class */
    public MonsterCard (String name, Rarities rarity, Editions edition, String set, String language,
    					String type, String summMethod, String atribute, String level,
    					String atk, String def) {
        super(name, rarity, edition, set, language);
        this.type = type;
        this.summMethod = summMethod;
        this.attribute = atribute;
        this.level = level;
        this.atk = atk;
        this.def = def;   
    }
    
    /* Method shows informations about monster card in console */
    @Override
    public void getInfo() {
        System.out.println(super.getName());
        System.out.println(super.getRarity());
        System.out.println(super.getEdition());
        System.out.println(super.getSet());
        System.out.println(super.getLanguage());
        System.out.println(type);
        System.out.println(attribute);
        System.out.println(level);
        System.out.println(atk);
        System.out.println(def);    
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public void setAtribute(String atribute) {
        this.attribute = atribute;
    }
    
    public void setAtk(String atk) {
        this.atk = atk;
    }
    
    public void setDef(String def) {
        this.def = def;
    }
    
    public void setSummMethod(String summMethod) {
        this.summMethod = summMethod;
    }

    public String getType() {
        return type;
    }
    
    public String getAtribute() {
        return attribute;
    }
    
    public String getLevel() {
        return level;
    }
    
    public String getAtk() {
        return atk;
    }
    
    public String getDef() {
        return def;
    }
    
    public String getSummMethod() {
        return summMethod;
    }
    
    
    
}



















