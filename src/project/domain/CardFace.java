package project.domain;

public enum CardFace {

	ACE(11, "Ace"), DEUCE(2, "Deuce"), THREE(3, "Three"), FOUR(4, "Four"), FIVE(5, "Five"), SIX(6, "Six"), SEVEN(7, "Seven"), EIGHT(8, "Eight"), NINE(9, "Nine"),
	TEN(10, "Ten"), JACK(10, "Jack"), KING(10, "King"), QUEEN(10, "Queen");

	private final int value;
	private final String name;

	private CardFace(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
