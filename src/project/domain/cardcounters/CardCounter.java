package project.domain.cardcounters;

import java.io.Serializable;
import project.domain.card.Card;
import project.domain.players.Player;

public abstract class CardCounter implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String name;

	public CardCounter(String name) {
		this.name = name;
	}

	public final void countCard(Player player, Card card) {
		player.addCardCountValue(this.calculateValue(card));
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	protected abstract double calculateValue(Card card);
}
