package project.domain.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import project.domain.Card;
import project.domain.CardFace;

public abstract class Participant {

	protected final List<Card> hand = new ArrayList<>();
	private int wins = 0;
	private int burned = 0;

	public final List<Card> emptyHand() {
		List<Card> cards = new ArrayList<>(this.hand);
		this.hand.clear();
		return cards;
	}

	public final void burned() {
		this.burned++;
	}

	public long countAces() {
		return this.hand.stream().filter(c -> c.getFace() == CardFace.ACE).count();
	}

	public List<Card> getHand() {
		return Collections.unmodifiableList(this.hand);
	}

	public int getValue() {
		int valueOutput = this.hand.stream().mapToInt(c -> c.getValue()).sum();
		if(this.countAces() > 0 && valueOutput <= 11) {
			valueOutput += 9;
		}
		return valueOutput;
	}

	public int getWins() {
		return this.wins;
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	@Override
	public String toString() {
		return this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
	}

	public void won() {
		this.wins++;
	}
}
