package project.domain.cardcounters;

import project.domain.card.Card;

public class Red7Counter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public Red7Counter() {
		super("Red 7");
	}

	@Override
	protected double calculateValue(Card card) {
		switch (card.getValue()) {
			case 8:
			case 9:
				break;
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return 1;
			case 10:
			case 11:
				return -1;
			case 7:
				return 0.5;
		}
		return 0;
	}
}
