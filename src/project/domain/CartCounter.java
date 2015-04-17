package project.domain;

import project.domain.card.Card;

public class CartCounter {

	public static void usedCard(Card card) {
		if(card.getValue() < 7) {
			value++;
		} else if(card.getValue() > 6) {
			value--;
		}
	}

	public static int getValue() {
		return value;
	}

	private static int value = 0;

}
