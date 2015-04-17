package project.domain.players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.card.Card;
import project.domain.card.CardFace;

public abstract class Participant {

	protected final ObservableList<Card> hand = FXCollections.observableArrayList();
	private final SimpleIntegerProperty twentyOne = new SimpleIntegerProperty();
	private final SimpleIntegerProperty burned = new SimpleIntegerProperty();
	private final SimpleIntegerProperty draw = new SimpleIntegerProperty();
	private final SimpleStringProperty name;
	private final SimpleIntegerProperty wins = new SimpleIntegerProperty();

	public Participant(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	public synchronized void blackJack() {
		this.twentyOne.set(this.twentyOne.get() + 1);
	}

	public ReadOnlyIntegerProperty blackJackProperty() {
		return this.twentyOne;
	}

	public synchronized void burned() {
		this.burned.set(this.burned.get() + 1);
	}

	public ReadOnlyIntegerProperty burnedProperty() {
		return this.burned;
	}

	public long countAces() {
		return this.hand.stream().filter(c -> c.getFace() == CardFace.ACE).count();
	}

	public synchronized void draw() {
		this.draw.set(this.draw.get() + 1);
	}

	public ReadOnlyIntegerProperty drawProperty() {
		return this.draw;
	}

	public final List<Card> emptyHand() {
		List<Card> cards = new ArrayList<>(this.hand);
		this.hand.clear();
		return cards;
	}

	public int getBurned() {
		return this.burned.get();
	}

	public ObservableList<Card> getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name.get();
	}

	public int getScore() {
		return Card.getScore(this.hand);
	}

	public int getWins() {
		return this.wins.get();
	}

	public SimpleStringProperty nameProperty() {
		return this.name;
	}

	public void reset() {
		this.burned.set(0);
		this.wins.set(0);
		this.draw.set(0);
		this.twentyOne.set(0);
	}

	@Override
	public String toString() {
		return this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
	}

	public ReadOnlyIntegerProperty winsProperty() {
		return this.wins;
	}

	public synchronized void won() {
		this.wins.set(this.wins.get() + 1);
	}
}
