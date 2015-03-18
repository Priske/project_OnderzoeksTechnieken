package project.domain;

public enum CardSuit {

	CLUBS("Clubs"), DIAMONDS("Diamonds"), HEARTS("Hearts"), SPADES("Spades");

	private final String name;

	private CardSuit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
