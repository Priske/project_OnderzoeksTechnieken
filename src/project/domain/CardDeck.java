package project.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**

 @author Ben
 */
public class CardDeck {

	private final int aantalDecks;
	private final Queue<Card> cards = new ArrayDeque<>();

	public CardDeck(int decks) {
		this.aantalDecks = decks;
		this.createDeck();
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

	private void shuffleCards(List<Card> cards) {
		IntStream.range(0, this.aantalDecks * 4).forEach(i -> Collections.shuffle(cards));
	}

	public void addCards(List<Card> cards) {
		cards.stream().forEach(c -> this.cards.offer(c));
	}
}
