/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

/**

 @author Ben
 */
public class Card {

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
