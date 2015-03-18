/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**

 @author Ben
 */
public class CardDeck {

	private final int aantalDecks;
	private final Stack<Card> cards = new Stack();

	public CardDeck(int decks) {
		this.aantalDecks = decks;
		this.createDeck();
	}

	public int getSize() {
		return this.cards.size();
	}

	public Card getTopCard() {
		return this.cards.pop();
	}

	public void reset() {
		this.createDeck();
	}

	@Override
	public String toString() {
		String output = "Aantal kaarten: " + this.getSize() + ", Aantal Decks: " + this.aantalDecks;
		output += this.cards.stream().map(c -> c.toString()).collect(Collectors.joining("\\n\t"));
		return output;
	}

	private List<Card> createCardSet() {
		List<Card> cardSet = new ArrayList<>();
		Arrays.asList(CardSuit.values()).forEach(suit -> {
			Arrays.asList(CardFace.values()).forEach(face -> {
				cardSet.add(new Card(suit, face));
			});
		});
		return cardSet;
	}

	private void createDeck() {
		this.cards.clear();
		IntStream.of(this.aantalDecks).forEach(i -> this.cards.addAll(this.createCardSet()));
		this.shuffleCards();
	}

	private void shuffleCards() {
		IntStream.of(this.aantalDecks * 2).forEach(i -> Collections.shuffle(this.cards));
	}
}
