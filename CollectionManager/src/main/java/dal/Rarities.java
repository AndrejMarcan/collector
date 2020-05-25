package main.java.dal;

/**
 * The Rarities enum serves as the list of all possible rarities of the cards.
 * 
 * @version		0.1 15. May 2020
 * @author 		Andrej Marcan
 */
public enum Rarities {
	COM("Common"),
	RARE("Rare"),
	SUR("Super Rare"),
	UR("Ultra Rare"),
	SR("Secret Rare"),
	ULT("Ultimate Rare"),
	GHOST("Ghost Rare"),
	SP("Special");
	
	String rarity;
	
	Rarities(String rarity){
		this.rarity = rarity;
	}
	
	public String getRarity() {
		return rarity;
	}
}
