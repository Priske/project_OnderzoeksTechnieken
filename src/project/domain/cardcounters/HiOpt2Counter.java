package project.domain.cardcounters;

import project.domain.card.Card;

public class HiOpt2Counter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public HiOpt2Counter() {
		super("Hi-Opt 2");
	}

	@Override
	protected double calculateValue(Card card) {
		switch (card.getValue()) {
			case 8:
			case 9:
			case 11:
				break;
			case 2:
			case 3:
			case 6:
			case 7:
				return 1;
			case 4:
			case 5:
				return 2;
			case 10:
				return -2;
		}
		return 0;
	}
}
