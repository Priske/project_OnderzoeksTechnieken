/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**

 @author Ben
 */
public class DeckOfCards {

	private final int aantalDecks;
	private ArrayList<Card> cards = new ArrayList<>();

	public DeckOfCards(int decks) {
		this.aantalDecks = decks;
		this.createDeck();
	}

	private void createDeck() {
		this.cards.clear();
		IntStream.of(this.aantalDecks).forEach(i -> this.cards.addAll(this.createCardSet()));
		this.shuffleCards();
	}

	private ArrayList<Card> createCardSet() {
		ArrayList<Card> cardSet = new ArrayList<>();
		Arrays.asList(CardSuit.values()).forEach(suit -> {
			Arrays.asList(CardFace.values()).forEach(face -> {
				cardSet.add(new Card(suit, face));
			});
		});
		return cardSet;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public void printDeck() {
		this.cards.stream().forEach(card -> System.out.println(card));
		System.out.println("Aantal kaarten: " + this.getSize() + ", Aantal Decks: " + this.aantalDecks);
	}

	private void shuffleCards() {
		IntStream.of(this.aantalDecks * 2).forEach(i -> Collections.shuffle(this.cards));
		//this.printDeck();
	}

	public int getSize() {
		return this.cards.size();
	}

	public void returnCardInDeck(Card card) {
		this.cards.add(card);
	}
}
