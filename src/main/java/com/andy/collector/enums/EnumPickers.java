package main.java.com.andy.collector.enums;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class EnumPickers {
	public EnumPickers(){
		System.out.println("EnumPickers created");
	}
	
	public Rarities rarityPicker(String rarityShort) {
		Rarities rarity = null;
		
		switch(rarityShort) {
        case "COM":
        	rarity = Rarities.COM;
        	break;
        case "RARE":
        	rarity = Rarities.RARE;
        	break;
        case "SUR":
        	rarity = Rarities.SUR;
        	break;
        case "UR":
        	rarity = Rarities.UR;
        	break;
        case "SR":
        	rarity = Rarities.SR;
        	break;
        case "ULT":
        	rarity = Rarities.ULT;
        	break;
        case "SP":
        	rarity = Rarities.SP;
        	break;
        case "GHOST":
        	rarity = Rarities.GHOST;
        	break;
        }
		return rarity;
	}
	
	public Rarities rarityPickerLonger(String rarityLong) {
		Rarities rarity = null;
		
		switch(rarityLong) {
        case "Common":
        	rarity = Rarities.COM;
        	break;
        case "Rare":
        	rarity = Rarities.RARE;
        	break;
        case "Super Rare":
        	rarity = Rarities.SUR;
        	break;
        case "Ultra Rare":
        	rarity = Rarities.UR;
        	break;
        case "Secret Rare":
        	rarity = Rarities.SR;
        	break;
        case "Ultimate Rare":
        	rarity = Rarities.ULT;
        	break;
        case "Special":
        	rarity = Rarities.SP;
        	break;
        case "Ghost Rare":
        	rarity = Rarities.GHOST;
        	break;
        }
		return rarity;
	}
	
	public Editions editionPicker(String edition) {
		Editions editions = null;
		
		switch(edition) {
        case "FE":
        	editions = Editions.FE;
        	break;
        case "UE":
        	editions = Editions.UE;
        	break;
        case "LE":
        	editions = Editions.LE;
        	break;
        }
		return editions;
	}
	
	public Editions editionPickerLonger(String editionLong) {
		Editions editions = null;
		
		switch(editionLong) {
        case "First Edition":
        	editions = Editions.FE;
        	break;
        case "Unlimited Edition":
        	editions = Editions.UE;
        	break;
        case "Limited Edition":
        	editions = Editions.LE;
        	break;
        }
		return editions;
	}
}
