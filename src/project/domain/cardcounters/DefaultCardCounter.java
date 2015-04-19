package project.domain.cardcounters;

import project.domain.card.Card;
import project.domain.players.Player;

public class DefaultCardCounter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public DefaultCardCounter() {
		super("No counting");
	}

	@Override
	protected double calculateValue(Card card) {
		return 0;
	}

	public void placeBet(Player player) {

	}
}
