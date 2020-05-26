package com.andy.collector.guiutils;

import com.andy.collector.dal.Editions;
import com.andy.collector.dal.Rarities;

public class EnumPickers {
	public static Rarities rarityPicker(String rarityShort) {
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
	
	public static Editions editionPicker(String edition) {
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
}
