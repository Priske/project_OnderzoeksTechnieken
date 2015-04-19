package project.domain.cardcounters;

import project.domain.card.Card;

public class HiOpt1Counter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public HiOpt1Counter() {
		super("Hi-Opt 2");
	}

	@Override
	protected double calculateValue(Card card) {
		switch (card.getValue()) {
			case 2:
			case 7:
			case 8:
			case 9:
			case 11:
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				return 1;
			case 10:
				return -1;
		}
		return 0;
	}
}
