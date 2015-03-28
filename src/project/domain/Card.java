package project.domain;

import java.util.List;

public class Card {

	public static final int getScore(List<Card> cards) {
		int valueOutput = cards.stream().mapToInt(c -> c.getValue()).sum();
		if(valueOutput > 21) {
			for (Card c : cards) {
				if(valueOutput <= 21) {
					break;
				}
				if(c.getFace() == CardFace.ACE) {
					valueOutput -= 10;
				}
			}
		}
		return valueOutput;
	}

	private final CardFace face;
	private final CardSuit suit;

	public Card(CardSuit suit, CardFace face) {
		this.suit = suit;
		this.face = face;
	}

	public CardFace getFace() {
		return this.face;
	}

	public int getValue() {
		return this.face.getValue();
	}

	@Override
	public String toString() {
		return this.face + " of " + this.suit;
	}

}
