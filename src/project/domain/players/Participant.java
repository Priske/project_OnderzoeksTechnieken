package project.domain.players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.card.Card;
import project.domain.card.CardFace;

public abstract class Participant {

	protected final ObservableList<Card> hand = FXCollections.observableArrayList();
	private int burned = 0;
	private final String name;
	private int wins = 0;

	public Participant(String name) {
		this.name = name;
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	public final void burned() {
		this.burned++;
	}

	public long countAces() {
		return this.hand.stream().filter(c -> c.getFace() == CardFace.ACE).count();
	}

	public final List<Card> emptyHand() {
		List<Card> cards = new ArrayList<>(this.hand);
		this.hand.clear();
		return cards;
	}

	public ObservableList<Card> getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return Card.getScore(this.hand);
	}

	public int getWins() {
		return this.wins;
	}

	@Override
	public String toString() {
		return this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
	}

	public void won() {
		this.wins++;
	}
}
