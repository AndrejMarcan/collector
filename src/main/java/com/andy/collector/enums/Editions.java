package com.andy.collector.enums;

public enum Editions {
	FE("First Edition", "gold"),
	LE("Limited Edition", "silver"),
	UE("Unlimited Edition", "silver");
	
	//TODO add private modifier
	String edition;
	String color;
	
	Editions(String edition, String color){
		this.edition = edition;
		this.color = color;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public String getColor() {
		return color;
	}
}
