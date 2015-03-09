/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**

 @author Ben
 */
public class Card {

	private final String face;
	private final String suit;

	public Card(String cardFace, String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;
	}

	public void printCard() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return this.face + " of " + this.suit;
	}

	public int getValue() {
		switch (this.face) {
			case "One":
				return 1;
			case "Deuce":
				return 2;
			case "Three":
				return 3;
			case "Four":
				return 4;
			case "Five":
				return 5;
			case "Six":
				return 6;
			case "Seven":
				return 7;
			case "Eight":
				return 8;
			case "Nine":
				return 9;
			case "Ten":
				return 10;
			case "Jack":
				return 10;
			case "King":
				return 10;
			case "Queen":
				return 10;
		}
		return 11;
	}
}
