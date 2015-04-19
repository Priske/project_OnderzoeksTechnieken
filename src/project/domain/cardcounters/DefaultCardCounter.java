package project.domain.cardcounters;

import project.domain.card.Card;

public class DefaultCardCounter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public DefaultCardCounter() {
		super("No counting");
	}

	@Override
	protected double calculateValue(Card card) {
		return 0;
	}
}
