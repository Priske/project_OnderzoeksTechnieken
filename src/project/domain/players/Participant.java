package project.domain.players;

import java.util.ArrayList;
import java.util.stream.Collectors;
import project.domain.Card;

public abstract class Participant {

	protected final ArrayList<Card> hand = new ArrayList<>();
	private int wins = 0;

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public int getWins() {
		return this.wins;
	}

	public void resetHand() {
		this.hand.clear();
	}

	@Override
	public String toString() {
		return this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
	}

	public void won() {
		this.wins++;
	}
}
