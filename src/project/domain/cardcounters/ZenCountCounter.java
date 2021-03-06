package project.domain.cardcounters;

import project.domain.card.Card;

public class ZenCountCounter extends CardCounter {

	private static final long serialVersionUID = 1L;

	public ZenCountCounter() {
		super("Zen Count");
	}

	@Override
	protected double calculateValue(Card card) {
		switch (card.getValue()) {
			case 8:
			case 9:
				break;
			case 2:
			case 3:
			case 7:
				return 1;
			case 11:
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
