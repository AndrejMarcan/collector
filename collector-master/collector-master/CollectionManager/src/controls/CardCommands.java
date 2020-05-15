/*
 * Copyright (c) ...
 */
package controls;

import card.Card;

/**
 * The CardCommands is generic class and provides method to edit card data in database
 * 
 * @version		0.1 15. May 2020
 * @author 		Andrej Marcan
 */
public class CardCommands<T extends Card> {
	private T t;
	
    /* Method editCard will update data in database */
	public void editCard(T t, String cell) {
		this.t = t;
		t.editCard(cell);
	}
}
