package project.domain.cardcounters;

import project.domain.card.Card;

public class Omega2Counter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public Omega2Counter() {
		super("Omega 2");
	}

	@Override
	protected double calculateValue(Card card) {
		switch (card.getValue()) {
			case 8:
			case 11:
				break;
			case 2:
			case 3:
			case 7:
				return 1;
			case 9:
				return -1;
			case 4:
			case 5:
			case 6:
				return 2;
			case 10:
				return -2;
		}
		return 0;
	}
}
