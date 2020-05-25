package main.java.dal;

public enum Editions {
	FE("First Edition", "gold"),
	LE("Limited Edition", "silver"),
	UE("Unlimited Edition", "silver");
	
	String edition;
	String color;
	
	Editions(String edition, String color){
		this.edition = edition;
		this.color = color;
	}
	
	String getEdition() {
		return edition;
	}
	
	String getColor() {
		return color;
	}
}
