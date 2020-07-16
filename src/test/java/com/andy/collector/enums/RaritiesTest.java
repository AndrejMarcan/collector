package com.andy.collector.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RaritiesTest {
	
	@Test
	void testRarityPickerShortMethod() {
	    assertEquals(Rarities.COM, Rarities.rarityPicker("COM"));
	    assertEquals(Rarities.RARE, Rarities.rarityPicker("RARE"));
	    assertEquals(Rarities.SR, Rarities.rarityPicker("SR"));
	    assertEquals(Rarities.GHOST, Rarities.rarityPicker("GHOST"));
	    assertEquals(Rarities.SUR, Rarities.rarityPicker("SUR"));
	    assertEquals(Rarities.SP, Rarities.rarityPicker("SP"));
	    assertEquals(Rarities.UR, Rarities.rarityPicker("UR"));
	    assertEquals(Rarities.ULT, Rarities.rarityPicker("ULT"));
	}
	
	@Test
	void testRarityPickerLongMethod() {
	    assertEquals(Rarities.COM, Rarities.rarityPickerLonger("Common"));
	    assertEquals(Rarities.RARE, Rarities.rarityPickerLonger("Rare"));
	    assertEquals(Rarities.SR, Rarities.rarityPickerLonger("Secret Rare"));
	    assertEquals(Rarities.GHOST, Rarities.rarityPickerLonger("Ghost Rare"));
	    assertEquals(Rarities.SUR, Rarities.rarityPickerLonger("Super Rare"));
	    assertEquals(Rarities.SP, Rarities.rarityPickerLonger("Special"));
	    assertEquals(Rarities.UR, Rarities.rarityPickerLonger("Ultra Rare"));
	    assertEquals(Rarities.ULT, Rarities.rarityPickerLonger("Ultimate Rare"));
	}
}
