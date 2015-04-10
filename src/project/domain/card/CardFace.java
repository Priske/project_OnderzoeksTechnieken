package project.domain.card;

public enum CardFace {

	ACE(11, "Ace"), DEUCE(2, "Deuce"), EIGHT(8, "Eight"), FIVE(5, "Five"), FOUR(4, "Four"), JACK(10, "Jack"), KING(10, "King"), NINE(9, "Nine"),
	QUEEN(10, "Queen"), SEVEN(7, "Seven"), SIX(6, "Six"), TEN(10, "Ten"), THREE(3, "Three");

	private final String name;
	private final int value;

	private CardFace(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
