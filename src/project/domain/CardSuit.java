package project.domain;

public enum CardSuit {

	SPADES("Spades"), HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs");

	private final String name;

	private CardSuit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
