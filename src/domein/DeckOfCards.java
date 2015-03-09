/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

/**

 @author Ben
 */
public class DeckOfCards {

	private static final int NUMBER_OF_CARDS = 52;
	private final int aantalDecks;
	private final ArrayList<Card> deck = new ArrayList<>();

	public DeckOfCards(int decks) {
		String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		this.aantalDecks = decks;
		for (int t = 0; t < decks; t++) {
			for (int c = 0; c < NUMBER_OF_CARDS; c++) {
				this.deck.add(new Card(faces[c % 13], suits[c / 13]));
			}
		}
	}

	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	public void printDeck() {
		this.deck.stream().forEach(card -> card.printCard());
		System.out.println("Aantal kaarten:" + this.deck.size() + ", Aantal Decks: " + this.aantalDecks);
	}

	public void shoufle() {
		IntStream.of(this.aantalDecks * 2).forEach(i -> Collections.shuffle(this.deck));
		this.printDeck();
	}
}
