package project.domain.players;

import java.util.ArrayList;
import java.util.stream.Collectors;
import project.domain.Card;
import project.domain.CardFace;

public abstract class Participant {

	protected final ArrayList<Card> hand = new ArrayList<>();
	private int wins = 0;

	public void clearHand() {
		this.hand.clear();
	}

	public int countAces() {
		return this.hand.stream().filter(c -> (c.getFace() == CardFace.ACE)).mapToInt(c -> 1).sum();
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public int getValue() {
		int valueOutput = this.hand.stream().mapToInt(c -> c.getValue()).sum();
		int aces = this.hand.stream().filter(c -> (c.getFace() == CardFace.ACE)).mapToInt(c -> 1).sum();
		if(aces == 0) {
			return valueOutput;
		} else {
			if(valueOutput <= 11) {
				valueOutput += 9;
			}
			return valueOutput;
		}
	}

	public int getWins() {
		return this.wins;
	}

	public void giveCard(Card card) {
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
