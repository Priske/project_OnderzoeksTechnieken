package project.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import project.domain.card.Card;
import project.domain.card.CardFace;
import project.domain.card.CardSuit;

public class CardDeck {

	private int aantalDecks;
	private final Queue<Card> cards = new ArrayDeque<>();

	public CardDeck(int decks) {
		this.aantalDecks = decks;
		this.reset();
	}

	public int getNumberDecks() {
		return this.aantalDecks;
	}

	public void setNumberDecks(int numberDecks) {
		if(numberDecks < 1) {
			throw new IllegalArgumentException("Number of decks cannot be smaller then 1.");
		}
		this.aantalDecks = numberDecks;
		this.reset();
	}

	public int getSize() {
		return this.cards.size();
	}

	public Card getNewCard() {
		return this.cards.poll();
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
		List<Card> newCards = new ArrayList<>();
		IntStream.range(0, this.aantalDecks).forEach(i -> newCards.addAll(this.createCardSet()));
		this.shuffleCards(newCards);
		this.cards.addAll(newCards);
	}

	public void shuffleCards() {
		List<Card> shuffleCards = new ArrayList<>(this.cards);
		this.shuffleCards(shuffleCards);
		this.cards.clear();
		this.cards.addAll(shuffleCards);
	}

	public void shuffleCards(List<Card> cards) {
		IntStream.range(0, this.aantalDecks * 4).forEach(i -> Collections.shuffle(cards));
	}

	public void addCards(List<Card> cards) {
		cards.stream().forEach(c -> this.cards.offer(c));
	}
}
