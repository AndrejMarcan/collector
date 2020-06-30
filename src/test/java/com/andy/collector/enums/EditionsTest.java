package com.andy.collector.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EditionsTest {
	@Test
	void testRarityPickerShortMethod() {
	    assertEquals(Editions.FE, Editions.editionPicker("FE"));
	    assertEquals(Editions.UE, Editions.editionPicker("UE"));
	    assertEquals(Editions.LE, Editions.editionPicker("LE"));
	}
	
	@Test
	void testRarityPickerLongMethod() {
		assertEquals(Editions.FE, Editions.editionPickerLonger("First Edition"));
	    assertEquals(Editions.UE, Editions.editionPickerLonger("Unlimited Edition"));
	    assertEquals(Editions.LE, Editions.editionPickerLonger("Limited Edition"));
	}
}
